package com.example.books.domain.bookSearch

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.books.data.BookDatabase
import com.example.books.network.BookApiFilter

class SearchBookViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates a BookDetailsViewModel
     *
     * @param modelClass the class to make a viewmodel from
     * @return The viewmodel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchBooksViewModel::class.java)) {
            return SearchBooksViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}