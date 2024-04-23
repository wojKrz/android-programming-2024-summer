package edu.androidprogrammingclasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.androidprogrammingclasses.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View =
    FragmentThirdBinding.inflate(inflater, container, false)
      .apply {

        argumentText.text = formatArguments()
        navigateButton.setOnClickListener {
          findNavController().navigate(R.id.actionNavigateToSecondScreen)
        }
      }.root

  private fun formatArguments(): String {
    return navArgs<ThirdFragmentArgs>().value.exampleArgument
  }
}
