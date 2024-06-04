package edu.androidprogrammingclasses

import android.view.View
import androidx.databinding.BindingAdapter
import edu.androidprogrammingclasses.start.TodoListViewState

@BindingAdapter(
  requireAll = true,
  value = ["visible"]
)
fun setVisible(view: View, visible: TodoListViewState) {
  view.visibility = when(visible) {
    is TodoListViewState.Loading -> View.VISIBLE
    else -> View.GONE
  }
}
