package edu.androidprogrammingclasses

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.androidprogrammingclasses.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val USER_HAS_SEEN_WELCOME = booleanPreferencesKey("userHasSeenWelcome")
val TEXT = stringPreferencesKey("exampleText")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()

    lifecycleScope.launch {
      val userHasSeenWelcome = dataStore.data
        .map { it[USER_HAS_SEEN_WELCOME] ?: false }
        .first()

      Log.d("Preferences", "User has seen welcome $userHasSeenWelcome")

      if (userHasSeenWelcome.not()) {
        findNavController(R.id.fragmentContainer).navigate(R.id.actionNavigateToWelcomeDialog)
      }
    }
  }

  private fun showWelcomeDialogIfNotVisible() {
    with(findNavController(R.id.fragmentContainer)) {
      if (currentDestination?.id != R.id.welcomeDialog) {
        navigate(R.id.actionNavigateToWelcomeDialog)
      }
    }
  }
}
