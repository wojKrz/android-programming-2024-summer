package edu.androidprogrammingclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.androidprogrammingclasses.databinding.FragmentStartBinding

class StartFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentStartBinding.inflate(inflater, container, false)

    binding.navigateButton.setOnClickListener {
      findNavController().navigate(R.id.actionNavigateToSecondScreen)
    }

    binding.navigateButton2.setOnClickListener {
      findNavController().navigate(
        StartFragmentDirections
          .actionNavigateToThirdScreenFromStartScreen("Second one")
      )
    }

    return binding.root
  }
}
