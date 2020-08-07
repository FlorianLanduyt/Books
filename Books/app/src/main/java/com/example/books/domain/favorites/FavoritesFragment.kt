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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.books.R
import com.example.books.databinding.FragmentFavoritesBinding
import com.example.books.databinding.FragmentSearchBooksBinding

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
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)




        binding.favoritesList.adapter = FavoritesAdapter(
            FavoritesAdapter.FavoriteListener {
                viewModel.getFavorites()
            }
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.favoritesList.layoutManager = LinearLayoutManager(this.context)
        observeFavorites(binding)

        return binding.root
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


}