package edu.androidprogrammingclasses.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import edu.androidprogrammingclasses.R
import edu.androidprogrammingclasses.databinding.FragmentStartBinding
import edu.androidprogrammingclasses.start.TodoListViewState.Loading
import edu.androidprogrammingclasses.start.TodoListViewState.Success

class TodoListFragment : Fragment() {

  private lateinit var binding: FragmentStartBinding

  private val viewModel: TodoListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentStartBinding.inflate(inflater, container, false)

    binding.navigateButton.setOnClickListener {
      findNavController().navigate(R.id.actionNavigateToSecondScreen)
    }

    binding.networkCallButton.setOnClickListener {
      viewModel.getDataFromNetwork()
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.responseLiveData.observe(viewLifecycleOwner) {
      when(it) {
        Loading -> binding.progressIndicator.visibility = VISIBLE
        is Success -> {
          binding.progressIndicator.visibility = GONE
          binding.text.text = it.result.toString()
        }
      }
    }
  }
}
