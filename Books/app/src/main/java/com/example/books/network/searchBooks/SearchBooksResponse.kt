package com.example.books.network.searchBooks

import com.squareup.moshi.Json

data class SearchBooksResponse(
    val totalItems: Int,
    @Json(name = "items") val books: List<Book>
)