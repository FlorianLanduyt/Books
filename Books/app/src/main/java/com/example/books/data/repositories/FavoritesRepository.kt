package com.example.books.data.repositories

import androidx.lifecycle.LiveData
import com.example.books.data.BookDatabase
import com.example.books.data.favorites.BookFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository (private val database: BookDatabase){
    var favorites: LiveData<List<BookFavorite>> = database.favoritesDao.getAll()



    suspend fun refreshFavoriteBooks() {
        withContext(Dispatchers.IO) {
            favorites = database.favoritesDao.getAll()
        }
    }

    suspend fun getBookFavorite(bookId: String): BookFavorite {
        return withContext(Dispatchers.IO) {
            database.favoritesDao.get(bookId)
        }
    }

    suspend fun insertBookFavorite(bookFavorite: BookFavorite) {
        withContext(Dispatchers.IO) {
            database.favoritesDao.insert(bookFavorite)
        }
    }

    suspend fun removeBookFavorite(id: String){
        withContext(Dispatchers.IO) {
            database.favoritesDao.delete(id)
        }
    }
}