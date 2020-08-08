package com.example.books.repositories

import androidx.lifecycle.LiveData
import com.example.books.data.BookDatabase
import com.example.books.data.favorites.BookFavorite
import com.example.books.data.toread.BookToRead
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.withContext

class ToReadRepository(private val database: BookDatabase) {
    var booksToRead: LiveData<List<BookToRead>> = database.toReadDao.getAll()


    suspend fun refreshBooksToRead(){
        withContext(Dispatchers.IO){
            booksToRead = database.toReadDao.getAll()
        }
    }

    suspend fun getBookToRead(bookId: String): BookToRead {
        return withContext(Dispatchers.IO) {
            database.toReadDao.get(bookId)
        }
    }

    suspend fun insertBookToRead(bookToRead: BookToRead) {
        withContext(Dispatchers.IO) {
            database.toReadDao.insert(bookToRead)
        }
    }

    suspend fun removeBookToRead(id: String){
        withContext(Dispatchers.IO) {
            database.toReadDao.delete(id)
        }
    }
}