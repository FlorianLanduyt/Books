package com.example.books.data.finishedBooks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.books.data.favorites.BookFavorite

@Dao
interface FinishedBooksDao {

    @Query("SELECT * FROM book_finished")
    fun getAll(): LiveData<List<FinishedBook>>

    @Query("SELECT * FROM book_finished WHERE book_id=:bookId")
    fun get(bookId: String): FinishedBook

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(finishedBook: FinishedBook)


    @Query("DELETE FROM book_finished WHERE book_id = :id")
    fun delete(id: String)



}