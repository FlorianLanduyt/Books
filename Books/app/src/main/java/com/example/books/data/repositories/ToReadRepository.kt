package com.example.books.data.repositories

import androidx.lifecycle.LiveData
import com.example.books.data.BookDatabase
import com.example.books.data.toread.BookToRead
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToReadRepository(private val database: BookDatabase) {
    var booksToRead: LiveData<List<BookToRead>> = database.toReadDao.getAll()


    /**
     * Refreshes the books you want to read by retrieving them from the database
     */
    suspend fun refreshBooksToRead(){
        withContext(Dispatchers.IO){
            booksToRead = database.toReadDao.getAll()
        }
    }

    /**
     * Gets a book you want to read from the database
     */
    suspend fun getBookToRead(bookId: String): BookToRead {
        return withContext(Dispatchers.IO) {
            database.toReadDao.get(bookId)
        }
    }

    /**
     * Inserts a book you want to read into the database
     */
    suspend fun insertBookToRead(bookToRead: BookToRead) {
        withContext(Dispatchers.IO) {
            database.toReadDao.insert(bookToRead)
        }
    }

    /**
     * Removes a book you want to read with given id
     *
     * @param id the id of the book you want to remove
     */
    suspend fun removeBookToRead(id: String){
        withContext(Dispatchers.IO) {
            database.toReadDao.delete(id)
        }
    }
}