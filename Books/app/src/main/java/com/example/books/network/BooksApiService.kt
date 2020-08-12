package com.example.books.network

import com.example.books.domain.models.SearchBooksResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

enum class BookApiFilter(val value: String) {
    SHOW_E_BOOKS("ebooks"), SHOW_FREE_EBOOKS("free-ebooks"), SHOW_ALL("partial")
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @GET("volumes")
    fun getBooksOnName(
        @Query("q") query: String
        ,@Query("filter") filter: String
    ): Deferred<SearchBooksResponse> //kind of coroutine job. provides canceling


    @GET("volumes")
    fun getBookById(
        @Query("q") id: String
    ): Deferred<SearchBooksResponse>
}



object BooksApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
