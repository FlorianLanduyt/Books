package com.example.books.data.books

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<DatabaseBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg book: DatabaseBook)


    @Query("DELETE FROM book_table")
    fun clear()
}