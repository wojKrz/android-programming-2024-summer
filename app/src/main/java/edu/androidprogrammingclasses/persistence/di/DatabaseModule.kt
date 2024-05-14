package edu.androidprogrammingclasses.persistence.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.androidprogrammingclasses.persistence.MyDatabase
import edu.androidprogrammingclasses.persistence.todos.TodoDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext applicationContext: Context): MyDatabase =
    Room.databaseBuilder(applicationContext, MyDatabase::class.java, "my_database.db")
      .fallbackToDestructiveMigration()
      .fallbackToDestructiveMigrationOnDowngrade()
      .build()

  @Provides
  @Singleton
  fun provideTodoDao(database: MyDatabase): TodoDao =
    database.getTodosDao()
}