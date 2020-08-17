package com.example.books.domain.favorites


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.books.R
import com.example.books.databinding.FragmentFavoritesBinding
import com.example.books.databinding.FragmentSearchBooksBinding
import com.example.books.domain.bookDetails.BookDetailsViewModel
import com.example.books.domain.bookDetails.BookDetailsViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)


        val application = requireNotNull(this.activity).application
        val viewModelFactory = FavoritesViewModelFactory(application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)




        binding.favoritesList.adapter = FavoritesAdapter(
            FavoritesAdapter.FavoriteListener { book, action ->
                when(action) {
                    "remove" -> viewModel.onBookFavoriteRemovedClicked(book.bookId)
                    "details" -> viewModel.navigateToBook(book.bookId)
                }
            }
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.favoritesList.layoutManager = LinearLayoutManager(this.context)

        setHasOptionsMenu(true)
        observeFavorites(binding)
        observeRemovedFavorites()
        observeNavigateToBook(viewModel)

        return binding.root
    }


    /**
     * Observes book to be navigated to
     *
     * @param viewModel the FavoritesViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeNavigateToBook(viewModel: FavoritesViewModel) {
        viewModel.bookToNavigateTo.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToBookDetailFragment(it)
                )
                viewModel.navigateToBookFinished()
            }
        })
    }

    /**
     * Observes favorites to check if the list is empty
     *
     * @param binding the FragmentFavoritesBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeFavorites(binding: FragmentFavoritesBinding) {
        viewModel.favoriteBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isEmpty()) {
                    binding.statusOfList.visibility = View.VISIBLE
                    binding.statusOfList.text = resources.getText(R.string.emptyFavoritesList)
                } else {
                    binding.statusOfList.visibility = View.GONE
                }
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
                viewModel.removeBookFavorite(it)
                viewModel.onBookFavoriteRemoved()
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
