package com.example.books.data.toread

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToReadDao {
    @Query("SELECT * FROM book_to_read")
    fun getAll(): LiveData<List<BookToRead>>

    @Query("SELECT * FROM book_to_read WHERE book_id=:bookId")
    fun get(bookId: String): BookToRead

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toRead: BookToRead)


    @Query("DELETE FROM book_to_read WHERE book_id = :id")
    fun delete(id: String)

}