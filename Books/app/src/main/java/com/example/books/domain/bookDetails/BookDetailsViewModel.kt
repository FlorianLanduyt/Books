package com.example.books.domain.bookDetails

import android.app.Application
import android.view.View
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

class BookDetailsViewModel(val book: String, app: Application) : AndroidViewModel(app) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _selectedProperty = MutableLiveData<Book>()

    val selectedProperty: LiveData<Book>
        get() = _selectedProperty

    init {
        getBook()

    }

    private fun getBook() {
        coroutineScope.launch {
            var getBookDeffered = BooksApi.retrofitService.getBook("",book)

            try {
                //_status.value = MyBooksApiStatus.LOADING
                var result = getBookDeffered.await()

                if(result.books.size > 0) {
                    _selectedProperty.value = result.books[0]
                }

                //status.value = MyBooksApiStatus.DONE
            } catch (e: Exception){
                //_status.value = MyBooksApiStatus.ERROR
                _selectedProperty.value = null
            }
        }
    }
}