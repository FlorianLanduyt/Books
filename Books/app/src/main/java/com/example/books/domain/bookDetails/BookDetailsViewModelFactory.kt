package com.example.books.domain.bookDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.books.domain.bookSearch.models.Book

class BookDetailsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    /**
     * Creates a BookDetailsViewModel
     *
     * @param modelClass the class to make a viewmodel from
     * @return The viewmodel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailsViewModel::class.java)) {
            return BookDetailsViewModel(
                application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
