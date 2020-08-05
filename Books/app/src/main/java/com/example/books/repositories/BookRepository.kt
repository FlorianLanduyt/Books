package com.example.books.repositories

import com.example.books.data.BookDatabase
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.bookSearch.models.SearchBooksResponse
import com.example.books.domain.bookSearch.models.asDatabaseModel
import com.example.books.network.BookApiFilter
import com.example.books.network.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository() {
//    suspend fun getBooksByFilter(title: String, filter: BookApiFilter?) {
//            var booksResponse = BooksApi.retrofitService.getBooksOnName(title, filter?.value?: BookApiFilter.SHOW_ALL.value )
//
//
//            return booksResponse.await()
//
//    }

    suspend fun getBooksByFitler(title: String,filter: BookApiFilter?): List<Book> {
            //val booklist: List<Book>

            //withContext(Dispatchers.IO){
                val searchResponse = BooksApi.retrofitService.getBooksOnName(title, filter?.value?: BookApiFilter.SHOW_ALL.value ).await()

                return searchResponse.books

                //database.bookDao.insertAll(*booklist.asDatabaseModel())

           // }
        }
    }
