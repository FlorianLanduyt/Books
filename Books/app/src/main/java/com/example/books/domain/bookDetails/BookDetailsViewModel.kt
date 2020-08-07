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
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = BookDatabase.getInstance(app)

    private val favoritsRepo = database.favoritesDao



    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    private val _authors = MutableLiveData<String>()
    val authors: LiveData<String>
        get() = _authors

    private val _inFavorites = MutableLiveData<Boolean>()
    val inFavorites: LiveData<Boolean>
        get() = _inFavorites

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




}