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

/*
 *  JSON library for Android and Kotlin. Parsing JSON into Kotlin objects.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


/*
*  HTTP client for Android and Kotlin that makes it easy to consume JSON or XML data which is parsed into POJOs
*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {

    /**
     * Gets all the books with a given title
     *
     * @param query the title of the book
     * @param filter the filter of the list (All books, e-books, free e-books)
     * @return the books
     */
    @GET("volumes")
    fun getBooksOnName(
        @Query("q") query: String
        , @Query("filter") filter: String
    ): Deferred<SearchBooksResponse> //kind of coroutine job. provides canceling


    /**
     * Gets a book on id
     *
     * @param id the id of the book
     * @return the book
     */
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
