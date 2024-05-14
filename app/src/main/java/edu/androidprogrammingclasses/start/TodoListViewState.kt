package edu.androidprogrammingclasses.start

import edu.androidprogrammingclasses.todo.Todo
import java.lang.Exception

sealed class TodoListViewState {
  data class Success(val result: List<Todo>) : TodoListViewState()
  data object Loading : TodoListViewState()
  data class Error(val e: Exception): TodoListViewState()
}
