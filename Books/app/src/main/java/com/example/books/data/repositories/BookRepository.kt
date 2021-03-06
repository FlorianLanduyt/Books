package com.example.books.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.books.data.BookDatabase
import com.example.books.data.books.asDomainModel
import com.example.books.domain.models.Book
import com.example.books.domain.models.asDatabaseModel
import com.example.books.network.BookApiFilter
import com.example.books.network.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val database: BookDatabase) {

    val books: LiveData<List<Book>> = Transformations.map(
        database.bookDao.getBooks()
    ) {
        it.asDomainModel()
    }


    /**
     * Refreshes the books after a search query has entered by sending a request to the back-end or by retrieving them from the database
     *
     * @param title the title of the book
     * @param filter the filter of the books (all, e-books or free e-books)
     */
    suspend fun refreshBooks(title: String,filter: BookApiFilter?) {
        withContext(Dispatchers.IO) {
                val searchResponse = BooksApi.retrofitService.getBooksOnName(title, filter?.value?: BookApiFilter.SHOW_ALL.value ).await()
                database.bookDao.clear()
                // The asterisk is the spread operator, allows you to pass in an array to a function expecting
                database.bookDao.insertAll(*searchResponse.asDatabaseModel())
            }
        }

    /**
     * Gets the book with the given id from the backend
     */
    suspend fun getBook(bookId: String): Book {
        return withContext(Dispatchers.IO) {
            BooksApi.retrofitService.getBookById(bookId).await().books[0]
        }
    }

    /**
     * Clears the book database
     */
    suspend fun clearBooks() {
        withContext(Dispatchers.IO) {
            database.bookDao.clear()
        }
    }
}

