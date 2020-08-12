package com.example.books.data.books

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    /*
    *  Returns all books added in the database
    */
    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<DatabaseBook>>


    /**
     * insert an amount of books into database
     *
     *  @param book the books you want to add to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg book: DatabaseBook)


    /**
     * Clears the books of the database
     */
    @Query("DELETE FROM book_table")
    fun clear()
}