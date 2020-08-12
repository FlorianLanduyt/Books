package com.example.books.domain.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase.Companion.getInstance
import com.example.books.data.favorites.BookFavorite
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.models.Book
import com.example.books.data.repositories.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application): ViewModel()
{
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val database = getInstance(application)
    private val favoritesRepo = FavoritesRepository(database)

    val favoriteBooks = favoritesRepo.favorites

    private val _status = MutableLiveData<MyBooksApiStatus>()
    val status: LiveData<MyBooksApiStatus>
        get() = _status

    private val _favoriteAdded = MutableLiveData<Boolean>()
    val favoriteAdded: LiveData<Boolean>
        get() = _favoriteAdded

    private val _favoriteRemoved = MutableLiveData<Boolean>()
    val favoriteRemoved: LiveData<Boolean>
        get() = _favoriteRemoved

    private val _removeFavoriteBook = MutableLiveData<String>()
    val removeFavoriteBook: LiveData<String>
        get() = _removeFavoriteBook


    private val _bookToNavigateTo = MutableLiveData<String>()
    val bookToNavigateTo: LiveData<String>
        get() = _bookToNavigateTo




    init {
        _status.value = MyBooksApiStatus.EMPTY

    }

    /**
     * Gets the list of favorite books
     */
    fun getFavorites(){
        coroutineScope.launch {
            try {
                _status.value = MyBooksApiStatus.LOADING
                favoritesRepo.refreshFavoriteBooks()
                _status.value = MyBooksApiStatus.DONE
            }catch (e: Exception){
                _status.value = MyBooksApiStatus.ERROR
            }
        }
    }


    /**
     * Inserts a book in the list of favorite books
     * @param book The book to be inserted
     */
    fun insertBookFavorite(book: Book?) {
        coroutineScope.launch {
            if (book != null) {
                if (favoritesRepo.getBookFavorite(book.id!!) == null) {
                    val bookFavorite =
                        BookFavorite(book.id, book.volumeInfo!!.title!!, book.volumeInfo.authors!!)

                    favoritesRepo.insertBookFavorite(bookFavorite)
                    onFavoriteAddClicked()
                } else {
                    favoritesRepo.removeBookFavorite(book.id)
                    onFavoriteRemoveClicked()
                }
            }
        }
    }

    /**
     * Checks if the book is in favorites in the detailsfragment
     */
    fun bookInFavorites(id: String){
        coroutineScope.launch {
            if(favoritesRepo.getBookFavorite(id) != null){
                onFavoriteAddClicked()
            } else {
                onFavoriteRemoveClicked()
            }
        }
    }


    /**
     *  Removes the book from the favorite list
     *
     *  @param bookId The id of the book to be removed
     */
    fun removeBookFavorite(bookId: String) {
        coroutineScope.launch {
            favoritesRepo.removeBookFavorite(bookId)
        }
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
     * Sets favoriteAdded to true
     */
    fun onFavoriteAddClicked() {
        _favoriteAdded.postValue(true)
    }

    /**
     * Sets favoriteAdded to false
     */
    fun onFavoriteAdded() {
        _favoriteAdded.postValue(false)
    }

    /**
     * Sets favoriteRemoved to true
     */
    fun onFavoriteRemoveClicked() {
        _favoriteRemoved.postValue(true)
    }

    /**
     * Sets favoriteRemoved to false
     */
    fun onFavoriteRemoved() {
        _favoriteRemoved.postValue(false)
    }

    /**
     * Assigns value to bookToNavigateTo
     *
     * @param id The id of the book to navigate to
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
}
