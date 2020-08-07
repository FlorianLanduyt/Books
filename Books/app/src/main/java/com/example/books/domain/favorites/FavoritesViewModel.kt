package com.example.books.domain.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.data.BookDatabase.Companion.getInstance
import com.example.books.data.favorites.BookFavorite
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.bookSearch.models.Book
import com.example.books.repositories.FavoritesRepository
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

    init {
        _status.value = MyBooksApiStatus.EMPTY
    }

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

    fun insertBookFavorite(book: Book?) {
        coroutineScope.launch {
            if (book != null) {
                if (favoritesRepo.getBookFavorite(book.id!!) == null) {
                    val bookFavorite =
                        BookFavorite(book.id, book.volumeInfo!!.title!!)


                    favoritesRepo.insertBookFavorite(bookFavorite)

                    Log.i("DEBUGDEBUG", "TESTTEST")
                } else {
                    favoritesRepo.removeBookFavorite(book.id)
                }
            }
        }
    }
}