package com.example.books.network.searchBooks

import com.squareup.moshi.Json

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLink?
)