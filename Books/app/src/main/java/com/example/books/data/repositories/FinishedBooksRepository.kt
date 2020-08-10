package com.example.books.data.repositories

import com.example.books.data.BookDatabase
import com.example.books.data.finishedBooks.FinishedBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FinishedBooksRepository(private val database: BookDatabase){
    var finishedBooks = database.finishedBooksDao.getAll()

    suspend fun getAll(){
        withContext(Dispatchers.IO){
            finishedBooks = database.finishedBooksDao.getAll()
        }
    }

    suspend fun getFinishedBook(bookId: String): FinishedBook {
        return withContext(Dispatchers.IO) {
            database.finishedBooksDao.get(bookId)
        }
    }

    suspend fun insertFinishedBook(finishedBook: FinishedBook) {
        withContext(Dispatchers.IO) {
            database.finishedBooksDao.insert(finishedBook)
        }
    }

    suspend fun removeFinishedBook(id: String){
        withContext(Dispatchers.IO) {
            database.finishedBooksDao.delete(id)
        }
    }
}
