package com.example.books.domain.yourbooks


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

    /**
     * Gets called by Android when the fragment gets inflated
     *
     * @param inflater the layout inflater
     * @param container the container
     * @param savedInstanceState the bundle created in onSaveInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentYourBooksBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = YourBooksViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(YourBooksViewModel::class.java)

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
        navigateToSearchBooksClicked()


        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }



    /**
     * Navigates to search fragment after click on search icon
     */
    private fun navigateToSearchBooksClicked() {
        binding.searchView.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToSearchBooksFragment())
        }
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


    /**
     * Navigates to finished book fragment
     */
    private fun navigateToFinishedBooksClicked() {
        binding.toFinishedBooks.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToFinishedBookFragment())
        }
    }

    /**
     * Navigates to favorite books fragment
     */
    private fun navigateToFavoriteBooksClicked() {
        binding.toFavoritesButton.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToFavoritesFragment())
        }
    }

    /**
     * Navigates to to read books fragment
     */
    private fun navigateToToReadBooksClicked() {
        binding.toReadButton.setOnClickListener {
            this.findNavController()
                .navigate(YourBooksFragmentDirections.actionYourBooksFragmentToToReadFragment())
        }
    }


    /**
     * Navigates details of a book
     */
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

    /**
     * Shows the overflow menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.about_overflow_menu, menu)
    }




    /**
     * When selecting an option of the overflow menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}
