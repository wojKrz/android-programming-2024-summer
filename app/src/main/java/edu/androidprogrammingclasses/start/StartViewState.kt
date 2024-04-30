package edu.androidprogrammingclasses.start

sealed class StartViewState {
  data class Success(val result: String) : StartViewState()
  data object Loading : StartViewState()
}
