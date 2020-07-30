package com.example.books.domain.searchBooks

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.network.BooksApi
import com.example.books.network.searchBooks.Book
import com.example.books.network.searchBooks.SearchBooksResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Duration

enum class MyBooksApiStatus { LOADING, ERROR, DONE, EMPTY }

class SearchBooksViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status


    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book>
    get() = _book



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        //_status.value = "Test"
        getBooks()
    }

    private fun getBooks(){
        coroutineScope.launch {
            var getBooksDeffered = BooksApi.retrofitService.getBooksOnName("superintelligence")

            try {

                var result = getBooksDeffered.await()

                if(result.books.size > 0) {
                    _book.value = result.books[0]
                }
                //_status.value = "Success: ${result.totalItems} boeken"
            } catch (e: Exception){
                _status.value = "Failure" + e.message
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}