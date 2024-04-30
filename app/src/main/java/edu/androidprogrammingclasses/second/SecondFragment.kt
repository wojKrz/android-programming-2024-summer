package edu.androidprogrammingclasses.second

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import edu.androidprogrammingclasses.databinding.FragmentSecondBinding
import edu.androidprogrammingclasses.start.StartViewModel

class SecondFragment : Fragment() {
  private val viewModel: SecondViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = FragmentSecondBinding.inflate(inflater, container, false)
    .apply {
      navigateButton.setOnClickListener {
        val resultText = input.text.toString()
        findNavController().navigate(
          SecondFragmentDirections.actionNavigateToThirdScreen(resultText)
        )
      }
    }.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d("ViewModels", viewModel.toString())
    Log.d("ViewModels", this.toString())
  }
}
