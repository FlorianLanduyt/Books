package com.example.books.Data.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/volumes?q="

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @GET("/")
    fun getMoviesOnName(
        @Query("t") titel: String,
        @Query("a") author: String?):
            Call<String>
}

object BookApi {
    val retrofitService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
