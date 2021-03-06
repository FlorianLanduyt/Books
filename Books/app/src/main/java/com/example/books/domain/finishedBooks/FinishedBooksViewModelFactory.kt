package com.example.books.domain.finishedbooks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FinishedBooksViewModelFactory(
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
        if (modelClass.isAssignableFrom(FinishedBooksViewModel::class.java)) {
            return FinishedBooksViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}