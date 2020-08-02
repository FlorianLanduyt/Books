package com.example.books.domain.bookSearch.models

import com.squareup.moshi.Json


data class SearchBooksResponse(
    val totalItems: Int,
    @Json(name = "items") val books: List<Book>
)