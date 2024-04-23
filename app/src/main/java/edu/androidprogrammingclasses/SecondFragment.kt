package edu.androidprogrammingclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.androidprogrammingclasses.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

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
}
