package com.example.books.domain.bookDetails

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class BookDetailsViewModel(val book: Book, app: Application) : AndroidViewModel(app) {
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    private val _authors = MutableLiveData<String>()
    val authors: LiveData<String>
        get() = _authors

    init {
        _selectedBook.value = book

        parseAuthors(book.volumeInfo?.authors)
    }

    private fun parseAuthors(authors: List<String>?) {
        val sb: StringBuilder = StringBuilder()


        authors?.let {
            it.forEach {
                sb.append(it)
                sb.append(", ")
            }


            //TODO
            if (sb.endsWith(','))
                sb.deleteCharAt(sb.lastIndex-1)
        }

        _authors.value = sb.toString();

    }

//    fun getBook() {
//        Log.i("DEBUG", "Test")
//        coroutineScope.launch {
//            var getBookDeffered = BooksApi.retrofitService.getBook(book)
//
//
//            try {
//                //_status.value = MyBooksApiStatus.LOADING
//                var result = getBookDeffered.await()
//
//                //Log.i("DEBUG", result.books[0].toString())
//
//                if(result.books.isNotEmpty()) {
//                    _selectedBook.value = result.books[0]
//
//                }
//
//                //status.value = MyBooksApiStatus.DONE
//            } catch (e: Exception){
//                //_status.value = MyBooksApiStatus.ERROR
//                _selectedBook.value = null
//            }
//        }
//    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

}