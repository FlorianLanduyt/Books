package com.example.books.data.favorites


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.books.data.DatabaseBook

/**
 * FavoritesDAO is an interface that provides all the methods that are necessary to manipulate the favorites
 * in the database
 *
 * [Dao] Data Access Objects are the main classes where you define your database interactions. $
 * They can include a variety of query methods.
 */
@Dao
interface FavoritesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: DatabaseBook)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(book: DatabaseBook)

    @Delete
    fun delete(book: DatabaseBook)


    @Query("DELETE FROM book_table")
    fun clear()

}