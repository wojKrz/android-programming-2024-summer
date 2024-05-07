package edu.androidprogrammingclasses.todo

import retrofit2.http.GET

interface TodosApiService {

  @GET("todos")
  suspend fun getTodos(): List<Todo>
}
