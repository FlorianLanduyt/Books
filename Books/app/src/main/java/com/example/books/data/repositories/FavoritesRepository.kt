package com.example.books.data.repositories

import androidx.lifecycle.LiveData
import com.example.books.data.BookDatabase
import com.example.books.data.favorites.BookFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository (private val database: BookDatabase){
    var favorites: LiveData<List<BookFavorite>> = database.favoritesDao.getAll()



    /**
     * Refreshes the favorite books by retrieving them from the database
     */
    suspend fun refreshFavoriteBooks() {
        withContext(Dispatchers.IO) {
            favorites = database.favoritesDao.getAll()
        }
    }

    /**
     * Gets a favorite book with given id from the database
     *
     * @param bookId the id of the favorite book
     */
    suspend fun getBookFavorite(bookId: String): BookFavorite {
        return withContext(Dispatchers.IO) {
            database.favoritesDao.get(bookId)
        }
    }

    /**
     * Inserts a book into the favorites
     *
     * @param bookFavorite the favorite book you want to add
     */
    suspend fun insertBookFavorite(bookFavorite: BookFavorite) {
        withContext(Dispatchers.IO) {
            database.favoritesDao.insert(bookFavorite)
        }
    }

    /**
     * Removes a favorite book from the database
     *
     * @param id the id of the favorite book you want to remove
     */
    suspend fun removeBookFavorite(id: String){
        withContext(Dispatchers.IO) {
            database.favoritesDao.delete(id)
        }
    }
}