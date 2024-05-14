package edu.androidprogrammingclasses.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.androidprogrammingclasses.persistence.todos.TodoDao
import edu.androidprogrammingclasses.persistence.todos.TodoEntity

@Database(
  version = 1,
  entities = [
    TodoEntity::class
  ]
)
abstract class MyDatabase : RoomDatabase() {
  
  abstract fun getTodosDao(): TodoDao
}
