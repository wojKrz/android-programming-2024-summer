package edu.androidprogrammingclasses.second

import android.util.Log
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {
  init {
    Log.d("ViewModels", "Second VM just got created! $this")
  }
}
