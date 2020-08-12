package com.example.books.data.books

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.books.domain.models.Book
import com.example.books.domain.models.VolumeInfo

@Entity(tableName = "book_table")
data class DatabaseBook (
    @PrimaryKey()
    val id: String,
    val volumeInfo: VolumeInfo?

)

/**
 * Maps a DatabaseBook to a book
 *
 * @return a book
 */
fun List<DatabaseBook>.asDomainModel(): List<Book>{
    return map {
        Book(
            it.id,
            it.volumeInfo
        )
    }
}