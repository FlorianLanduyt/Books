package com.example.books.domain.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)


        val application = requireNotNull(this.activity).application
        val viewModelFactory = FavoritesViewModelFactory(application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)

        viewModel.getFavorites()



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

        observeFavorites(binding)
        observeRemovedFavorites(binding)
        observeNavigateToBook(viewModel)

        return binding.root
    }


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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedFavorites(binding: FragmentFavoritesBinding) {
        viewModel.removeFavoriteBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeBookFavorite(it)
                viewModel.onBookFavoriteRemoved()
            }
        })
    }


}
