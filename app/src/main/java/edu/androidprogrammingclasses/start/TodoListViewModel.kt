package edu.androidprogrammingclasses.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.androidprogrammingclasses.MyApplication
import edu.androidprogrammingclasses.start.TodoListViewState.Loading
import edu.androidprogrammingclasses.start.TodoListViewState.Success
import edu.androidprogrammingclasses.todo.TodoMapper
import edu.androidprogrammingclasses.todo.TodosApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
  private val getDataFromNetworkUseCase: GetDataFromNetworkUseCase
) : ViewModel() {


  private val todosListRepository = TodosListRepository()

  private val toggleTodoCompletionUseCase = ToggleTodoCompletionUseCase(
    todosListRepository
  )

  private val _responseLiveData = MutableLiveData<TodoListViewState>()
  val responseLiveData: LiveData<TodoListViewState> = _responseLiveData

  fun getDataFromNetwork() {
    viewModelScope.launch {
      _responseLiveData.value = Loading

      val response = async(Dispatchers.IO) {
        getDataFromNetworkUseCase.invoke().run(::Success)
      }.await()

      withContext(Dispatchers.Main) {
        response
          .let(_responseLiveData::setValue)
      }
    }
  }

  fun toggleTodoState(todoId: Int) {
    _responseLiveData.value.apply {
      when (this) {
        is Success -> {
          toggleTodoCompletionUseCase.invoke(todoId)
          _responseLiveData.value = Success(todosListRepository.getTodos())
        }

        else -> {}
      }
    }
  }
}
