package com.example.books.domain.finishedbooks

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.models.Book
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
    val navigateToBookDetail: LiveData<String>
        get() = _navigateToBookDetail


    /**
     * Get the list of finished books
     */
    fun getFinishedBooks(){
        coroutineScope.launch {
            finishedBooksRepo.getAll()
        }
    }


    /**
     * Inserts a book in the finished books list
     *
     * @param book the book you want to add
     */
    fun insertFinishedBook(book: Book?) {
        coroutineScope.launch {
            book?.let {
                if (finishedBooksRepo.getFinishedBook(book.id!!) == null) {
                    val finishedBook =
                        FinishedBook(book.id, book.volumeInfo!!.title!!, book.volumeInfo.authors!! )

                    finishedBooksRepo.insertFinishedBook(finishedBook)
                    onFinishedBookAddClicked()
                } else {
                    finishedBooksRepo.removeFinishedBook(book.id)
                    onFinishedBookRemoveClicked()
                }
            }
        }
    }

    /**
     * Removes the book out of the finished books list
     */
    fun removeFinishedBook(bookId: String){
        coroutineScope.launch {
            finishedBooksRepo.removeFinishedBook(bookId)
        }
    }


    /**
     * Sets finishedBooksRemoved to true
     */
    private fun onFinishedBookRemoveClicked() {
        _finishedBookRemoved.postValue(true)
    }

    /**
     * Sets finishedBooksRemoved to false
     */
    fun onFinishedBookRemoved(){
        _finishedBookRemoved.postValue(false)
    }

    /**
     * Sets finishedBookAdded to true
     */
    fun onFinishedBookAddClicked() {
        _finishedBookAdded.postValue(true)
    }

    /**
     * Sets finishedBookAdded to false
     */
    fun onFinishedBookAdded(){
        _finishedBookAdded.postValue(false)
    }


    /**
     * Assings value to removeFinishedBook
     *
     * @param finishedBook the id of the finished book
     */
    fun onFinishedBookRemovedClicked(finishedBook: String){
        _removeFinishedBook.value = finishedBook
    }


    /**
     * Sets value of removeFinishedBook to null
     */
    fun onBookFinishedBookRemoved() {
        _removeFinishedBook.value = null
    }


    /**
     * Assings value to navigateToBookDetail
     *
     * @param finishedBook the id of the finished book
     */
    fun onBookFinishedClicked(bookId: String){
        _navigateToBookDetail.postValue(bookId)
    }

    /**
     * Sets value of navigateToBookDetail to null
     */
    fun onBookFinishedNavigated(){
        _navigateToBookDetail.postValue(null)
    }
}