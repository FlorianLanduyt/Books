package com.example.books.domain.bookSearch.models

import com.example.books.data.books.DatabaseBook
import com.squareup.moshi.Json


data class SearchBooksResponse(
    val totalItems: Int,
    @Json(name = "items") val books: List<Book>
)

fun SearchBooksResponse.asDatabaseModel(): Array<DatabaseBook> {
    return this.books.map {
        DatabaseBook(
            it.id!!,
            it.volumeInfo
        )
    }.toTypedArray()
}
