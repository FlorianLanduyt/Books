package com.example.books.domain.toRead

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase.Companion.getInstance
import com.example.books.data.toread.BookToRead
import com.example.books.domain.models.Book
import com.example.books.data.repositories.ToReadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToReadViewModel (application: Application): ViewModel(){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val database = getInstance(application)
    private val toReadRepository = ToReadRepository(database)

    val toReadBooks = toReadRepository.booksToRead

    private val _toReadAdded = MutableLiveData<Boolean>()
    val toReadAdded: LiveData<Boolean>
        get() = _toReadAdded

    private val _toReadRemoved = MutableLiveData<Boolean>()
    val toReadRemoved: LiveData<Boolean>
        get() = _toReadRemoved

    private val _removeToReadBook = MutableLiveData<String>()
    val removeToReadBook: LiveData<String>
        get() = _removeToReadBook

    private val _bookToNavigateTo = MutableLiveData<String>()
    val bookToNavigateTo: LiveData<String>
        get() = _bookToNavigateTo

    fun getToReads(){
        coroutineScope.launch {
            toReadRepository.refreshBooksToRead()
        }
    }

    fun insertBookToRead(book: Book?){
        coroutineScope.launch {
            book?.let{
                if(toReadRepository.getBookToRead(book.id!!) == null){
                    val bookToRead =
                        BookToRead(book.id, book.volumeInfo!!.title!!, book.volumeInfo.authors!!)
                    toReadRepository.insertBookToRead(bookToRead)
                    onToReadBookAddClicked()
                } else {
                    toReadRepository.removeBookToRead(book.id)
                    onToReadBookRemoveClicked()
                }
            }
        }
    }

    fun removeBookToRead(bookId: String){
        coroutineScope.launch {
            toReadRepository.removeBookToRead(bookId)
        }
    }

    fun onBookToReadRemovedClicked(toReadBookId: String){
        _removeToReadBook.value = toReadBookId
    }

    fun onBookToReadRemoved() {
        _removeToReadBook.value = null
    }

    fun onToReadBookAddClicked() {
        _toReadAdded.postValue(true)
    }

    fun onToReadBookAdded(){
        _toReadAdded.postValue(false)
    }

    fun onToReadBookRemoveClicked() {
        _toReadRemoved.postValue(true)
    }

    fun onToReadRemoved() {
        _toReadRemoved.postValue(false)
    }

    fun navigateToBook(id: String){
        _bookToNavigateTo.postValue(id)
    }

    fun navigateToBookFinished(){
        _bookToNavigateTo.postValue(null)
    }
}
