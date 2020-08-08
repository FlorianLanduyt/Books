package com.example.books.data.toread


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.books.domain.bookSearch.models.VolumeInfo

@Entity(tableName = "book_to_read")
data class BookToRead(
    @PrimaryKey @ColumnInfo(name = "book_id")
    val bookId: String,

    @ColumnInfo(name = "title")
    val title: String
)
