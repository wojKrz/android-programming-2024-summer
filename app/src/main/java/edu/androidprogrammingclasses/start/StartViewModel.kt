package edu.androidprogrammingclasses.start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.androidprogrammingclasses.start.StartViewState.Loading
import edu.androidprogrammingclasses.start.StartViewState.Success
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class StartViewModel : ViewModel() {
  private val netClient = OkHttpClient()

  private val _responseLiveData = MutableLiveData<StartViewState>()
  val responseLiveData: LiveData<StartViewState> = _responseLiveData

  fun getDataFromNetwork() {
    val request = createGetTodosRequest()

    Log.d("Call", "Starting")

    viewModelScope.launch {
      _responseLiveData.value = Loading

      val responseDeferred: Deferred<Response> = makeCallOnIO(this, request)
      val response = responseDeferred.await()

      withContext(Dispatchers.Main) {
        response.body
          .run(::mapBodyOrGetDefault)
          .run(::Success)
          .let(_responseLiveData::setValue)
      }
    }
  }

  private fun mapBodyOrGetDefault(body: ResponseBody?): String = body?.string() ?: "No reponse"

  private fun createGetTodosRequest(): Request =
    Request.Builder()
      .url("https://jsonplaceholder.typicode.com/todos")
      .build()

  private fun makeCallOnIO(scope: CoroutineScope, request: Request): Deferred<Response> =
    scope.async(Dispatchers.IO) {
      Log.d("Call", "Got response")
      netClient.newCall(request).execute()
    }
}
