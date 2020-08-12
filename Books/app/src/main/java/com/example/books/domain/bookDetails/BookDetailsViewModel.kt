package com.example.books.domain.bookDetails

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase
import com.example.books.data.repositories.BookRepository
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.bookSearch.models.SearchBooksResponse
import com.example.books.network.BooksApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.coroutineContext

enum class BookApiStatus { LOADING, ERROR, DONE }

class BookDetailsViewModel(app: Application) : AndroidViewModel(app) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = BookDatabase.getInstance(app)
    private val bookRepo = BookRepository(database)

    private val _status = MutableLiveData<BookApiStatus>()
    val status: LiveData<BookApiStatus>
        get() = _status

    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    private val _authors = MutableLiveData<String>()
    val authors: LiveData<String>
        get() = _authors


    private val _moreText = MutableLiveData<Boolean>()
    val moreText: LiveData<Boolean>
        get() = _moreText


    /**
     * Gets the bookdetails of a book with given id
     *
     * @param bookId the id of the book
     */
    fun getBookProperties(bookId: String) {
        coroutineScope.launch {
            try {
                _status.value = BookApiStatus.LOADING
                _selectedBook.value = bookRepo.getBook(bookId)
                _status.value = BookApiStatus.DONE
            } catch (e: Exception){
                _status.value = BookApiStatus.ERROR
            }
        }
    }

    /**
     * Sets the value of more text to true
     */
    fun moreText(){
        _moreText.postValue(true)
    }

    /**
     * Sets the value of more text to false
     */
    fun lessText(){
        _moreText.postValue(false)
    }

    /**
     * Will be called when the viewmodel is no longer used and will be destroyed
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}