package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.persistence.todos.TodoDao
import edu.androidprogrammingclasses.todo.Todo
import edu.androidprogrammingclasses.todo.TodoMapper
import edu.androidprogrammingclasses.todo.TodosApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.UnknownHostException
import javax.inject.Inject

class GetDataFromNetworkUseCase @Inject constructor(
  private val todosApiService: TodosApiService,
  private val todosDao: TodoDao,
  private val mapper: TodoMapper,
) {

  suspend fun invoke(): List<Todo> =
    try {
      todosApiService.getTodos().apply { storeData(this) }
    } catch (e: UnknownHostException) {
      todosDao.getTodos().map(mapper::mapEntityToTodo)
    }

  private suspend fun storeData(data: List<Todo>) {
    data.map(mapper::mapTodoToEntity)
      .apply { todosDao.insert(this) }
  }
}
