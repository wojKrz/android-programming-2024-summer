package edu.androidprogrammingclasses.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.androidprogrammingclasses.start.TodoListViewState.Loading
import edu.androidprogrammingclasses.start.TodoListViewState.Success
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

class TodoListViewModel : ViewModel() {
  private val netClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { this.setLevel(BODY) })
    .build()

  private val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(netClient)
    .build()

  private val todosService = retrofit.create(TodosApiService::class.java)

  private val todosListRepository = TodosListRepository()

  private val getDataFromNetworkUseCase = GetDataFromNetworkUseCase(
    todosService,
    todosListRepository,
    viewModelScope
  )

  private val toggleTodoCompletionUseCase = ToggleTodoCompletionUseCase(
    todosListRepository
  )


  private val _responseLiveData = MutableLiveData<TodoListViewState>()
  val responseLiveData: LiveData<TodoListViewState> = _responseLiveData

  fun getDataFromNetwork() {
    viewModelScope.launch {
      _responseLiveData.value = Loading

      val response = getDataFromNetworkUseCase.invoke()

      withContext(Dispatchers.Main) {
        response
          .run(::Success)
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
