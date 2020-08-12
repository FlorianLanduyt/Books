package com.example.books.domain.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class FavoritesViewModelFactory (
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
        if(modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            return FavoritesViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}