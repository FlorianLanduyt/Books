package com.example.books.domain.bookDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.books.domain.bookSearch.models.Book

class BookDetailsViewModelFactory(
    private val book: Book,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailsViewModel::class.java)) {
            return BookDetailsViewModel(book, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
