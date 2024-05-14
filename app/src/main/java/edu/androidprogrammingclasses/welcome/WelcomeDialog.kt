package edu.androidprogrammingclasses.welcome

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import edu.androidprogrammingclasses.USER_HAS_SEEN_WELCOME
import edu.androidprogrammingclasses.dataStore
import edu.androidprogrammingclasses.databinding.DialogWelcomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeDialog : DialogFragment() {

  private lateinit var binding: DialogWelcomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DialogWelcomeBinding.inflate(inflater, container, false)
    binding.goToTheAppButton.setOnClickListener { onGoToAppClicked() }
    return binding.root
  }

  override fun onDismiss(dialog: DialogInterface) {
    super.onDismiss(dialog)

    GlobalScope.launch {
      requireContext().dataStore
        .edit {
          it[USER_HAS_SEEN_WELCOME] = true
        }
    }
  }

  private fun onGoToAppClicked() = dismiss()
}
