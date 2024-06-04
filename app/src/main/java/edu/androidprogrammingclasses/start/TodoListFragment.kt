package edu.androidprogrammingclasses.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import edu.androidprogrammingclasses.R
import edu.androidprogrammingclasses.TEXT
import edu.androidprogrammingclasses.dataStore
import edu.androidprogrammingclasses.databinding.FragmentStartBinding
import edu.androidprogrammingclasses.start.TodoListViewState.Error
import edu.androidprogrammingclasses.start.TodoListViewState.Loading
import edu.androidprogrammingclasses.start.TodoListViewState.Success
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment() {

  private lateinit var binding: FragmentStartBinding

  private val viewModel: TodoListViewModel by viewModels()

  private val todosAdapter = TodosListAdapter(::onToggleClicked)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentStartBinding.inflate(inflater, container, false)

    binding.lifecycleOwner = this
    binding.viewModel = viewModel

    binding.navigateButton.setOnClickListener {
      findNavController().navigate(R.id.actionNavigateToSecondScreen)
    }

    binding.networkCallButton.setOnClickListener {
      viewModel.getDataFromNetwork()
    }

    binding.writeButton.setOnClickListener {
      binding.editText
        .text
        .toString()
        .also(viewModel::setTextData)
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.todoList.apply {
      adapter = todosAdapter
      layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    lifecycleScope.launch {
      requireContext().dataStore
        .data
        .map { it[TEXT] ?: "String was empty" }
        .collect {
        }
    }

    viewModel.responseLiveData.observe(viewLifecycleOwner) {
      when (it) {
        Loading -> {  }
        is Success -> {
          todosAdapter.data = it.result
          todosAdapter.notifyDataSetChanged()
        }

        is Error -> {
          Log.e("Todos fetch", it.e.message.orEmpty())
        }
      }
    }
  }

  private fun onToggleClicked(todoId: Int) {
    viewModel.toggleTodoState(todoId)
  }
}
