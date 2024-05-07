package edu.androidprogrammingclasses.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.recyclerview.widget.RecyclerView
import edu.androidprogrammingclasses.R
import edu.androidprogrammingclasses.start.TodosListAdapter.ViewHolder
import edu.androidprogrammingclasses.todo.Todo

class TodosListAdapter(
  private val onToggleClick: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

  var data: List<Todo> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_todo, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
    val todo = data[position]

    nameTextView.text = todo.title
    completedToggle.isChecked = todo.completed
    completedToggle.setOnClickListener {
      onToggleClick(todo.id)
    }
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView = view.findViewById<TextView>(R.id.todoName)
    val completedToggle = view.findViewById<AppCompatToggleButton>(R.id.isCompletedToggle)
  }
}
