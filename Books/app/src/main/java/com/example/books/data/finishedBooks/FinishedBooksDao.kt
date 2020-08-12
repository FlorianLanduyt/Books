package com.example.books.data.finishedBooks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.books.data.favorites.BookFavorite

@Dao
interface FinishedBooksDao {

    /**
     * Gives all the books you have finished reading from the database
     *
     * @return a list of the finished books
     */
    @Query("SELECT * FROM book_finished")
    fun getAll(): LiveData<List<FinishedBook>>

    /**
     * Gives a book with a given id from the finished books
     *
     * @param bookId the id of the finished book you want to delete
     * @return the finished book
     */
    @Query("SELECT * FROM book_finished WHERE book_id=:bookId")
    fun get(bookId: String): FinishedBook

    /**
     * Inserts a finished book in the database
     *
     * @param the finished book you want to add
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(finishedBook: FinishedBook)

    /**
     * Deletes a finished book out of the database
     *
     * @param id the id of the finished book
     */
    @Query("DELETE FROM book_finished WHERE book_id = :id")
    fun delete(id: String)



}