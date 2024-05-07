package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.todo.Todo

sealed class TodoListViewState {
  data class Success(val result: List<Todo>) : TodoListViewState()
  data object Loading : TodoListViewState()
}
