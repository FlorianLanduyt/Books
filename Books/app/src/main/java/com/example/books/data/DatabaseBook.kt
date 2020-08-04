package com.example.books.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.bookSearch.models.VolumeInfo

@Entity(tableName = "book_table")
data class DatabaseBook (
    @PrimaryKey
    val id: String,
    val volumeInfo: VolumeInfo?

)

fun List<DatabaseBook>.asDomainModel(): List<Book>{
    return map {
        Book(
            it.id,
            it.volumeInfo
        )
    }

}