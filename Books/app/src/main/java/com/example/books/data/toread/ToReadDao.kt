package com.example.books.data.toread

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToReadDao {

    /**
     * Refreshes the books you want to read by retrieving them from the database
     *
     * @return The list of books you want to read from the database
     */
    @Query("SELECT * FROM book_to_read")
    fun getAll(): LiveData<List<BookToRead>>

    /**
     * Gives the book you want to read with given id
     *
     * @param bookId the id of the book you want to get
     * @return the book you want to read
     */
    @Query("SELECT * FROM book_to_read WHERE book_id=:bookId")
    fun get(bookId: String): BookToRead

    /**
     * Inserts the book you want to read in the database
     *
     * @param toRead the book you want to read
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toRead: BookToRead)


    /**
     * Removes a book you want to read from the database
     *
     * @param id the id of the book you want to read
     */
    @Query("DELETE FROM book_to_read WHERE book_id = :id")
    fun delete(id: String)

}