package com.example.books.domain.yourbooks


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.books.R
import com.example.books.databinding.FragmentYourBooksBinding
import com.example.books.domain.favorites.FavoritesAdapter
import com.example.books.domain.finishedbooks.FinishedBookAdapter
import com.example.books.domain.toRead.ToReadAdapter
import java.security.Provider

/**
 * A simple [Fragment] subclass.
 */
class YourBooksFragment : Fragment() {

    private lateinit var binding: FragmentYourBooksBinding
    private lateinit var viewModel: YourBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentYourBooksBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = YourBooksViewModelFactory(application)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(YourBooksViewModel::class.java)

        viewModel.refreshBooksToRead()
        viewModel.refreshFavoriteBooks()
        viewModel.refreshFinishedBooks()

        //viewModel.checkForEmptyFavoriteList()

        binding.toReadBooksList.adapter = ToReadAdapter(
            ToReadAdapter.ToReadListener { book, action ->
                when (action) {
                    "remove" -> {
                        viewModel.onBookToReadRemovedClicked(book.bookId)
                    }
                    "details" -> viewModel.navigateToBook(book.bookId)
                }
            }
        )

        binding.favoriteBooksList.adapter = FavoritesAdapter(
            FavoritesAdapter.FavoriteListener { book, action ->
                when (action) {
                    "remove" -> {
                        viewModel.onBookFavoriteRemovedClicked(book.bookId)
                    }
                    "details" -> viewModel.navigateToBook(book.bookId)
                }
            }
        )

        binding.finishedBooksList.adapter =
            FinishedBookAdapter(FinishedBookAdapter.FinishedBookListener { book, action ->
                when (action) {
                    "remove" -> {
                        viewModel.onFinishedBookRemovedClicked(book.bookId)
                    }
                    "details" -> viewModel.navigateToBook(book.bookId)
                }
            })


        binding.favoriteBooksList.layoutManager = LinearLayoutManager(this.context)
        binding.toReadBooksList.layoutManager = LinearLayoutManager(this.context)
        binding.finishedBooksList.layoutManager = LinearLayoutManager(this.context)

        observeNavigateToBook()
        observeRemovedFavorites()
        observeRemovedFinished()
        observeRemovedToRead()
        navigateToToReadBooksClicked()
        navigateToFavoriteBooksClicked()
        navigateToFinishedBooksClicked()
        observeEmptyList()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    private fun observeEmptyList() {
        viewModel
    }

//    private fun observeEmptyFavoriteList() {
//        viewModel.statusFavorites.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                when (it) {
//                    YourBooksStatus.EMPTY -> {
//                        binding.statusFavorites.visibility = View.VISIBLE
//                        binding.toFavoritesButton.visibility = View.GONE
//                    }
//                    else -> {
//                        binding.statusFavorites.visibility = View.GONE
//                        binding.toFavoritesButton.visibility = View.VISIBLE
//                    }
//                }
//            }
//        })
//    }


    private fun navigateToFinishedBooksClicked() {
        binding.toFinishedBooks.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToFinishedBookFragment())
        }
    }

    private fun navigateToFavoriteBooksClicked() {
        binding.toFavoritesButton.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToFavoritesFragment())
        }
    }

    private fun navigateToToReadBooksClicked() {
        binding.toReadButton.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToToReadFragment())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeNavigateToBook() {
        viewModel.bookToNavigateTo.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    YourBooksFragmentDirections.actionYourBooksFragmentToBookDetailFragment(it)
                )
                viewModel.navigateToBookFinished()
            }
        })
    }

    /**
     * Observes the removed favorites to check that the book gets deleted
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedFavorites() {
        viewModel.removeFavoriteBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeFavorite(it)
                viewModel.onBookFavoriteRemoved()
//                viewModel.checkForEmptyFavoriteList()
            }
        })
    }

    /**
     * Observes the removed finished to check that the book gets deleted
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedFinished() {
        viewModel.removeFinishedBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeFinished(it)
                viewModel.onFinishedBookRemoved()
            }
        })
    }

    /**
     * Observes the removed to read to check that the book gets deleted
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedToRead() {
        viewModel.removeBookToRead.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeToRead(it)
                viewModel.onBookToReadRemoved()
            }
        })
    }


}
