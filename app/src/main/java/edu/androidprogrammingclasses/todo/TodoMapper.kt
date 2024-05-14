package edu.androidprogrammingclasses.todo

import edu.androidprogrammingclasses.persistence.todos.TodoEntity
import javax.inject.Inject

class TodoMapper @Inject constructor() {

  fun mapTodoToEntity(todo: Todo): TodoEntity = with(todo) {
    TodoEntity(
      id = id,
      userId = userId,
      title = title,
      completed = completed
    )
  }

  fun mapEntityToTodo(entity: TodoEntity): Todo = with(entity) {
    Todo(
      id = id,
      userId = userId,
      title = title,
      completed = completed
    )
  }
}
