package com.example.books.data.repositories

import com.example.books.data.BookDatabase
import com.example.books.data.finishedBooks.FinishedBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FinishedBooksRepository(private val database: BookDatabase){
    var finishedBooks = database.finishedBooksDao.getAll()


    /**
     * Refreshes all finished books by retrieving them from the database
     */
    suspend fun getAll(){
        withContext(Dispatchers.IO){
            finishedBooks = database.finishedBooksDao.getAll()
        }
    }

    /**
     * Gets a finished book with given id
     *
     * @param bookId the id of the finished book
     */
    suspend fun getFinishedBook(bookId: String): FinishedBook {
        return withContext(Dispatchers.IO) {
            database.finishedBooksDao.get(bookId)
        }
    }

    /**
     * Inserts a finished book in the database
     *
     * @param finishedBook the finished book you want to add
     */
    suspend fun insertFinishedBook(finishedBook: FinishedBook) {
        withContext(Dispatchers.IO) {
            database.finishedBooksDao.insert(finishedBook)
        }
    }


    /**
     * Removes a finished book from the database
     *
     * @param id the id of the finished book you want to remove
     */
    suspend fun removeFinishedBook(id: String){
        withContext(Dispatchers.IO) {
            database.finishedBooksDao.delete(id)
        }
    }
}
