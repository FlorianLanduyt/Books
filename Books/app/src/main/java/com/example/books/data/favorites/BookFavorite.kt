package com.example.books.data.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_favorite")
data class BookFavorite(
    @PrimaryKey @ColumnInfo(name = "book_id")
    val bookId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "authors")
    val authors: List<String>


)
