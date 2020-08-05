package com.example.books.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.books.data.BookDatabase
import com.example.books.data.books.asDomainModel
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.bookSearch.models.asDatabaseModel
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


    suspend fun refreshBeers(title: String,filter: BookApiFilter?) {
        withContext(Dispatchers.IO) {
                val searchResponse = BooksApi.retrofitService.getBooksOnName(title, filter?.value?: BookApiFilter.SHOW_ALL.value ).await()
                database.bookDao.clear()
                // The asterisk is the spread operator, allows you to pass in an array to a function expecting
                database.bookDao.insertAll(*searchResponse.asDatabaseModel())
            }
        }
    }

