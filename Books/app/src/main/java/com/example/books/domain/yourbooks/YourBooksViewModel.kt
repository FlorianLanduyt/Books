package com.example.books.domain.yourbooks

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase
import com.example.books.data.repositories.FavoritesRepository
import com.example.books.data.repositories.FinishedBooksRepository
import com.example.books.data.repositories.ToReadRepository
import com.example.books.domain.bookSearch.MyBooksApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class YourBooksStatus { LOADING, ERROR, DONE, EMPTY }


class YourBooksViewModel(application: Application) : ViewModel(){
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val database = BookDatabase.getInstance(application)

    private val toReadRepository = ToReadRepository(database)
    private val finishedBooksRepository = FinishedBooksRepository(database)
    private val favoritesRepository = FavoritesRepository(database)

    private val _statusFavorites= MutableLiveData<YourBooksStatus>()
    val statusFavorites:LiveData<YourBooksStatus>
        get() = _statusFavorites

    private val _statusFinishedBooks= MutableLiveData<YourBooksStatus>()
    val statusFinishedBooks:LiveData<YourBooksStatus>
        get() = _statusFinishedBooks

    private val _statusBookToRead= MutableLiveData<YourBooksStatus>()
    val statusBookToRead:LiveData<YourBooksStatus>
        get() = _statusBookToRead

    private val _generalStatus= MutableLiveData<YourBooksStatus>()
    val generalStatus:LiveData<YourBooksStatus>
        get() = _generalStatus

    private val _bookToNavigateTo = MutableLiveData<String>()
    val bookToNavigateTo: LiveData<String>
        get() = _bookToNavigateTo


    private val _removeFavoriteBook = MutableLiveData<String>()
    val removeFavoriteBook: LiveData<String>
        get() = _removeFavoriteBook

    private val _removeFinishedBook = MutableLiveData<String>()
    val removeFinishedBook: LiveData<String>
        get() = _removeFinishedBook

    private val _removeBookToRead = MutableLiveData<String>()
    val removeBookToRead: LiveData<String>
        get() = _removeBookToRead

    val favoriteBooks = favoritesRepository.favorites
    val toReadBooks = toReadRepository.booksToRead
    val finishedBooks = finishedBooksRepository.finishedBooks

    init {
        _statusFavorites.value = YourBooksStatus.EMPTY
        _statusBookToRead.value = YourBooksStatus.EMPTY
        _statusFinishedBooks.value = YourBooksStatus.EMPTY
    }



    fun refreshFavoriteBooks(){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                favoritesRepository.refreshFavoriteBooks()
//                checkForEmptyFavoriteList()
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

//    fun checkForEmptyFavoriteList(){
////        val viewModelScope = CoroutineScope(Job() + Dispatchers.Main)
////        viewModelScope.launch(){
//            if (favoriteBooks.value == null || favoriteBooks.value!!.isEmpty()){
//                _statusFavorites.postValue(YourBooksStatus.EMPTY)
//            } else {
//                _statusFavorites.postValue(YourBooksStatus.DONE)
//            }
//        }
//
//    }

    fun refreshBooksToRead(){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                favoritesRepository.refreshFavoriteBooks()
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

    fun refreshFinishedBooks(){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                favoritesRepository.refreshFavoriteBooks()
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

    fun removeToRead(bookId: String){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                toReadRepository.removeBookToRead(bookId)
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

    fun removeFavorite(bookId: String){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                favoritesRepository.removeBookFavorite(bookId)
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

    fun removeFinished(bookId: String){
        coroutineScope.launch {
            try {
                _statusFavorites.postValue(YourBooksStatus.LOADING)
                finishedBooksRepository.removeFinishedBook(bookId)
                _statusFavorites.postValue(YourBooksStatus.DONE)
            } catch (e: Exception){
                _statusFavorites.postValue(YourBooksStatus.ERROR)
            }
        }
    }

    /**
     * Assigns a value to bookToNavigateTo
     *
     * @param id the value
     */
    fun navigateToBook(id: String){
        _bookToNavigateTo.postValue(id)
    }

    /**
     * Sets the value of bookToNavigateTo to null
     */
    fun navigateToBookFinished(){
        _bookToNavigateTo.postValue(null)
    }


    /**
     * Assigns a value for the book to be removed out of the finished books list
     *
     * @param finishedBookId the id of the book to be removed
     */
    fun onFinishedBookRemovedClicked(finishedBookId: String){
        _removeFinishedBook.value = finishedBookId
    }

    /**
     * Sets the value of the book to be deleted to null
     */
    fun onFinishedBookRemoved() {
        _removeFinishedBook.value = null
    }

    /**
     * Assigns a value for the book to be removed out of the favorites list
     *
     * @param favoriteBookId the id of the book to be removed
     */
    fun onBookFavoriteRemovedClicked(favoriteBookId: String){
        _removeFavoriteBook.value = favoriteBookId
    }

    /**
     * Sets the value of the book to be deleted to null
     */
    fun onBookFavoriteRemoved() {
        _removeFavoriteBook.value = null
    }

    /**
     * Assigns a value for the book to be removed out of the To read list
     *
     * @param favoriteBookId the id of the book to be removed
     */
    fun onBookToReadRemovedClicked(bookToReadId: String){
        _removeBookToRead.value = bookToReadId
    }

    /**
     * Sets the value of the book to be deleted to null
     */
    fun onBookToReadRemoved() {
        _removeBookToRead.value = null
    }
}