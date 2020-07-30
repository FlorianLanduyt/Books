package com.example.books.domain.searchBooks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.Data.network.BooksApi
import com.example.books.Data.network.BooksApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MyBooksApiStatus { LOADING, ERROR, DONE, EMPTY }

class SearchBooksViewModel : ViewModel() {
    //private val _status = MutableLiveData<MyBooksApiStatus>()

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        _response.value = "Test"
        getBooks()
    }

    private fun getBooks(){
        BooksApi.retrofitService.getBooksOnName("Superintelligence").enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure" + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body().toString()
            }
        })
    }
}