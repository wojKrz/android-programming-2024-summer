package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.todo.Todo
import edu.androidprogrammingclasses.todo.TodosApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class GetDataFromNetworkUseCase(
  private val todosApiService: TodosApiService,
  private val repository: TodosListRepository,
  private val coroutineScope: CoroutineScope
) {

  suspend fun invoke(): List<Todo> =
    coroutineScope
      .async(Dispatchers.IO) {
        todosApiService.getTodos()
          .also(repository::saveTodos)
      }.await()
}
