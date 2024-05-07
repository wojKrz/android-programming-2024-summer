package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.todo.Todo

class TodosListRepository {
  private var todos: MutableList<Todo> = mutableListOf()

  fun getTodoById(todoId: Int): Todo {
    val todoIndex = todos.indexOfFirst { it.id == todoId }
    return todos[todoIndex]
  }

  fun saveTodos(todos: List<Todo>) {
    this.todos = todos.toMutableList()
  }

  fun updateTodo(todo: Todo) {
    val todoIndex = todos.indexOfFirst { it.id == todo.id }
    todos[todoIndex] = todo
  }

  fun getTodos(): List<Todo> = todos
}
