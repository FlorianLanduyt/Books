package com.example.books.domain.bookSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.network.BooksApi
import com.example.books.domain.bookSearch.models.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MyBooksApiStatus { LOADING, ERROR, DONE, EMPTY }

class SearchBooksViewModel : ViewModel() {

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status


    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
    get() = _books

    private val _authors = MutableLiveData<List<String>>()
    val authors: LiveData<List<String>>
    get() = _authors

    private val _navigateToSelectedBook = MutableLiveData<String>()
    val navigateToSelectedBook: LiveData<String>
        get() = _navigateToSelectedBook


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _status.value = MyBooksApiStatus.EMPTY
    }

    fun getBooks(
        title: String
    ){
        coroutineScope.launch {
            var getBooksDeffered = BooksApi.retrofitService.getBooksOnName(title)

            try {
                _status.value = MyBooksApiStatus.LOADING
                var result = getBooksDeffered.await()

                if(result.books.size > 0) {
                    _books.value = result.books
                }

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

    fun displayBookDetails(book: String){
        _navigateToSelectedBook.value = book
    }

    fun displayBookDetailsComplete(){
        _navigateToSelectedBook.value = null
    }
}

























