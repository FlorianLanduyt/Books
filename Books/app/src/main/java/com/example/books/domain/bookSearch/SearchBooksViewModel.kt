package com.example.books.domain.bookSearch

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase.Companion.getInstance
import com.example.books.domain.models.Book
import com.example.books.network.BookApiFilter
import com.example.books.data.repositories.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MyBooksApiStatus { LOADING, ERROR, DONE, EMPTY }

class SearchBooksViewModel(private val application: Application) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getInstance(application)
    private val bookRepo = BookRepository(database)

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status

    val books = bookRepo.books


    private val _authors = MutableLiveData<List<String>>()
    val authors: LiveData<List<String>>
        get() = _authors

    private val _navigateToSelectedBook = MutableLiveData<Book>()
    val navigateToSelectedBook: LiveData<Book>
        get() = _navigateToSelectedBook

    init {
        _status.value = MyBooksApiStatus.EMPTY
        clearBooks()
    }


    /**
     * Gets the books
     *
     * @param title The title of the book
     * @param filter The filter of the booklist
     */
    fun getBooks(title: String, filter: BookApiFilter) {
        coroutineScope.launch {

            try {
                _status.value = MyBooksApiStatus.LOADING
                bookRepo.refreshBooks(title, filter)
                _status.value = MyBooksApiStatus.DONE
            } catch (e: Exception) {
                bookRepo.clearBooks()
                _status.value = MyBooksApiStatus.ERROR

            }
        }

    }


    /**
     * Clears the books in the database
     */
    private fun clearBooks() {
        coroutineScope.launch {
            bookRepo.clearBooks()
        }
    }


    /**
     * Will be called when the viewmodel is no longer used and will be destroyed
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    /**
     * Assigns a value to navigateToSelectedBook
     *
     * @param book the value to assign
     */
    fun displayBookDetails(book: Book) {
        _navigateToSelectedBook.value = book
    }

    /**
     * Clears the value of navigateToSelectedBook
     *
     * @param beerId the value to assign
     */
    fun displayBookDetailsComplete() {
        _navigateToSelectedBook.value = null
    }

    fun updateFilter(title: String, filter: BookApiFilter) {
        getBooks(title, filter)
    }
//
//    fun searchFieldSelect(){
//        _editFieldClicked.postValue(true)
//    }
//
//    fun searchFieldUnselect(){
//        _editFieldClicked.postValue(false)
//    }
}

























