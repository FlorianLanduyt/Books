package com.example.books.domain.bookDetails


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.books.R
import com.example.books.databinding.FragmentDetailBookBinding
import com.example.books.databinding.FragmentSearchBooksBinding
import com.example.books.domain.bookSearch.SearchBooksViewModel
import com.example.books.domain.favorites.FavoritesViewModel
import com.example.books.domain.favorites.FavoritesViewModelFactory
import com.example.books.network.BookApiFilter
import kotlinx.coroutines.supervisorScope

/**
 * A simple [Fragment] subclass.
 */
class BookDetailFragment : Fragment() {
    private lateinit var detailViewModel: BookDetailsViewModel
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FragmentDetailBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_book, container, false)

        val args = BookDetailFragmentArgs.fromBundle(arguments!!).book


        val application = requireNotNull(activity).application
        val binding = FragmentDetailBookBinding.inflate(inflater)

        val bookDetailsViewModelFactory = BookDetailsViewModelFactory(args, application)
        val favoritesViewModelFactory = FavoritesViewModelFactory(application)

        detailViewModel = ViewModelProviders.of(this, bookDetailsViewModelFactory).get(BookDetailsViewModel::class.java)
        favoritesViewModel = ViewModelProviders.of(this, favoritesViewModelFactory).get(FavoritesViewModel::class.java)

        observeBook(binding)
        observeFavoriteAdded(binding)
        observerFavoriteRemoved(binding)
        observeMoreText(binding)



        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel
        binding.viewModelFavorites = favoritesViewModel


        return binding.root
    }



     @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBook(binding: FragmentDetailBookBinding) {
        detailViewModel.selectedBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.book = it
                binding.descriptionText.text = detailViewModel.book.volumeInfo!!.description!!.subSequence(
                    0,
                    100
                ).toString().plus(" ...")
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeMoreText(binding: FragmentDetailBookBinding) {
        detailViewModel.moreText.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    binding.descriptionText.text = detailViewModel.book.volumeInfo!!.description!!.toString()
                    binding.moreText.visibility = View.GONE
                    binding.lessText.visibility = View.VISIBLE
                } else {
                    binding.descriptionText.text = detailViewModel.book.volumeInfo!!.description!!.subSequence(
                        0,
                        100
                    ).toString().plus(" ...")
                    binding.moreText.visibility = View.VISIBLE
                    binding.lessText.visibility = View.GONE
                }

            }
        })
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeFavoriteAdded(binding: FragmentDetailBookBinding?) {
        favoritesViewModel.favoriteAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.buttonAddToFavorites?.setImageResource(R.drawable.favorite_in_list)
                    favoritesViewModel.onFavoriteAdded()
                }
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observerFavoriteRemoved(binding: FragmentDetailBookBinding?) {
        favoritesViewModel.favoriteRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.buttonAddToFavorites?.setImageResource(R.drawable.favorite)
                    favoritesViewModel.onFavoriteRemoved()
                }
            }
        })
    }




}



