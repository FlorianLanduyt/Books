package com.example.books.data.books

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.books.data.DatabaseBook

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    fun getBook(): LiveData<List<DatabaseBook>>


}