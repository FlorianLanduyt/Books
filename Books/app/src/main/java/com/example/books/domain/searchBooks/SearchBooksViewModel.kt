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

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status


    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
    get() = _books



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _status.value = MyBooksApiStatus.EMPTY
        //getBooks("")
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
}