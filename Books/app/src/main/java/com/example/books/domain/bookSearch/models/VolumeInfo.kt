package com.example.books.domain.bookSearch.models

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLink?
)