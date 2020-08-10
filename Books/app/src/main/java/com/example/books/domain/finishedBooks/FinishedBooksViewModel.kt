package com.example.books.domain.finishedbooks

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.bookSearch.models.Book
import com.example.books.data.repositories.FinishedBooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FinishedBooksViewModel (application: Application) : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val database = BookDatabase.getInstance(application)
    private val finishedBooksRepo = FinishedBooksRepository(database)

    val finishedBooks = finishedBooksRepo.finishedBooks

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status

    private val _finishedBookAdded = MutableLiveData<Boolean>()
    val finishedBookAdded: LiveData<Boolean>
        get() = _finishedBookAdded

    private val _finishedBookRemoved = MutableLiveData<Boolean>()
    val finishedBookRemoved: LiveData<Boolean>
        get() = _finishedBookRemoved

    private val _removeFinishedBook = MutableLiveData<String>()
    val bookToRemove: LiveData<String>
        get() = _removeFinishedBook

    private val _navigateToBookDetail = MutableLiveData<String>()
    val navigateToBookDetail
        get() = _navigateToBookDetail


    fun getFinishedBooks(){
        coroutineScope.launch {
            finishedBooksRepo.getAll()
        }
    }


    fun insertFinishedBook(book: Book?) {
        coroutineScope.launch {
            if (book != null) {
                if (finishedBooksRepo.getFinishedBook(book.id!!) == null) {
                    val finishedBook =
                        FinishedBook(book.id, book.volumeInfo!!.title!!)

                    finishedBooksRepo.insertFinishedBook(finishedBook)
                    onFinishedBookAddClicked()
                } else {
                    finishedBooksRepo.removeFinishedBook(book.id)
                    onFinishedBookRemoveClicked()
                }
            }
        }
    }

    fun removeFinishedBook(bookId: String){
        coroutineScope.launch {
            finishedBooksRepo.removeFinishedBook(bookId)
        }
    }


    private fun onFinishedBookRemoveClicked() {
        _finishedBookRemoved.postValue(true)
    }

    fun onFinishedBookAddClicked() {
        _finishedBookAdded.postValue(true)
    }

    fun onFinishedBookAdded(){
        _finishedBookAdded.postValue(false)
    }

    fun onFinishedBookRemoved(){
        _finishedBookRemoved.postValue(false)
    }

    fun onFinishedBookRemovedClicked(finishedBook: String){
        _removeFinishedBook.value = finishedBook
    }

    fun onBookFinishedBookRemoved() {
        _removeFinishedBook.value = null
    }

    fun onBookFinishedClicked(bookId: String){
        _navigateToBookDetail.value = bookId
    }

    fun onBookFinishedNavigated(){
        _navigateToBookDetail.value = null
    }
}