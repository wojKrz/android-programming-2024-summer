package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.persistence.todos.TodoDao
import edu.androidprogrammingclasses.todo.Todo
import edu.androidprogrammingclasses.todo.TodoMapper
import edu.androidprogrammingclasses.todo.TodosApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.UnknownHostException

class GetDataFromNetworkUseCase(
  private val todosApiService: TodosApiService,
//  private val repository: TodosListRepository,
  private val todosDao: TodoDao,
  private val mapper: TodoMapper,
  private val coroutineScope: CoroutineScope
) {

  suspend fun invoke(): List<Todo> =
    coroutineScope
      .async(Dispatchers.IO) {
        try {
          todosApiService.getTodos().apply { storeData(this) }
        } catch (e: UnknownHostException) {
          todosDao.getTodos().map(mapper::mapEntityToTodo)
        }
      }.await()

  private suspend fun storeData(data: List<Todo>) {
    data.map(mapper::mapTodoToEntity)
      .apply { todosDao.insert(this) }
  }
}
