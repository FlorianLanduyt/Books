package com.example.books.domain.bookSearch

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.books.domain.bookDetails.BookDetailsViewModel
import com.example.books.domain.bookSearch.models.Book
import com.example.books.network.BookApiFilter

class SearchBookViewModelFactory(
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchBooksViewModel::class.java)) {
            return SearchBooksViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}