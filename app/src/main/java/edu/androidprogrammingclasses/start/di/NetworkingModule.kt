package edu.androidprogrammingclasses.start.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.androidprogrammingclasses.todo.TodosApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {

  @Provides
  @Singleton
  fun provideNetClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { this.setLevel(BODY) })
    .build()


  @Provides
  @Singleton
  fun provideRetrofit(netClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(netClient)
    .build()

  @Provides
  @Singleton
  fun provideTodosApi(retrofit: Retrofit): TodosApiService =
    retrofit.create(TodosApiService::class.java)
}
