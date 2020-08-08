package com.example.books.data.favorites


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.books.data.books.DatabaseBook

/**
 * FavoritesDAO is an interface that provides all the methods that are necessary to manipulate the favorites
 * in the database
 *
 * [Dao] Data Access Objects are the main classes where you define your database interactions. $
 * They can include a variety of query methods.
 */
@Dao
interface FavoritesDao {

    @Query("SELECT * FROM book_favorite")
    fun getAll(): LiveData<List<BookFavorite>>

    @Query("SELECT * FROM book_favorite WHERE book_id=:bookId")
    fun get(bookId: String): BookFavorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: BookFavorite)


    @Query("DELETE FROM book_favorite WHERE book_id = :id")
    fun delete(id: String)



}