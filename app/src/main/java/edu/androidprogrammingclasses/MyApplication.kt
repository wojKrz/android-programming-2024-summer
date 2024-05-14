package edu.androidprogrammingclasses

import android.app.Application
import androidx.room.Room
import edu.androidprogrammingclasses.persistence.MyDatabase

class MyApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    database = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "my_database.db")
      .fallbackToDestructiveMigration()
      .fallbackToDestructiveMigrationOnDowngrade()
      .build()
  }

  companion object {
    lateinit var database: MyDatabase
  }
}
