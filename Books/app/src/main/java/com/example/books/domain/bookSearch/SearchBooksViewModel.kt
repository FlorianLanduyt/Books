package com.example.books.domain.bookSearch

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase
import com.example.books.data.BookDatabase.Companion.getInstance
import com.example.books.network.BooksApi
import com.example.books.domain.bookSearch.models.Book
import com.example.books.network.BookApiFilter
import com.example.books.repositories.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MyBooksApiStatus { LOADING, ERROR, DONE, EMPTY }

class SearchBooksViewModel(
) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val bookRepo = BookRepository()

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status


    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
    get() = _books



    private val _authors = MutableLiveData<List<String>>()
    val authors: LiveData<List<String>>
    get() = _authors

    private val _navigateToSelectedBook = MutableLiveData<Book>()
    val navigateToSelectedBook: LiveData<Book>
        get() = _navigateToSelectedBook

//    private var bookRepository: BookRepository()

    init {
        _status.value = MyBooksApiStatus.EMPTY
    }

    fun getBooks(title: String, filter: BookApiFilter){
        coroutineScope.launch {

            try {
                _status.value = MyBooksApiStatus.LOADING

                _books.value = bookRepo.getBooksByFitler(title, filter)

                _status.value = MyBooksApiStatus.DONE
            } catch (e: Exception){
                _status.value = MyBooksApiStatus.ERROR
                _books.value = ArrayList()
            }



        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayBookDetails(book: Book){
        _navigateToSelectedBook.value = book
    }

    fun displayBookDetailsComplete(){
        _navigateToSelectedBook.value = null
    }

    fun updateFilter(title: String, filter: BookApiFilter){
        Log.d("DEBUGDEBUG",filter.value)
        //_books.value = null
        getBooks(title,filter)
    }
}

























