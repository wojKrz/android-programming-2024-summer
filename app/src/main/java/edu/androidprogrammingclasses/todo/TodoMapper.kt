package edu.androidprogrammingclasses.todo

import edu.androidprogrammingclasses.persistence.todos.TodoEntity

class TodoMapper {

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
