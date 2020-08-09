package com.example.books.domain.finishedbooks


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.databinding.FragmentFavoritesBinding

import com.example.books.databinding.FragmentFinishedBooksBinding

/**
 * A simple [Fragment] subclass.
 */
class FinishedBookFragment : Fragment() {
    private lateinit var viewModel: FinishedBooksViewModel
    private lateinit var binding: FragmentFinishedBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finished_books, container, false)


        val application = requireNotNull(this.activity).application
        val viewModelFactory = FinishedBooksViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FinishedBooksViewModel::class.java)

        viewModel.getFinishedBooks()

//        val adapter = FinishedBookAdapter(
//            FinishedBookAdapter.FinishedBookListener { book, action ->
//
//                    when (action) {
//                        "details" -> viewModel.onBookFinishedClicked(book.bookId)
//                        "removeBook" -> viewModel.onFinishedBookRemovedClicked(book.bookId)
//                    }
//            })

        val adapter =
            FinishedBookAdapter(FinishedBookAdapter.FinishedBookListener{
                viewModel.onFinishedBookRemovedClicked(it.bookId)
            })

        binding.finishedBooksList.adapter = adapter
        binding.finishedBooksList.layoutManager = LinearLayoutManager(this.context)

        observeFinishedBooks(binding)
        observeRemovedToRead(binding)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeFinishedBooks(binding: FragmentFinishedBooksBinding) {
        viewModel.finishedBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isEmpty()) {
                    binding.statusOfFinishedBooksList.visibility = View.VISIBLE
                    binding.statusOfFinishedBooksList.text = resources.getText(R.string.emptyFavoritesList)
                } else {
                    binding.statusOfFinishedBooksList.visibility = View.GONE
                }
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedToRead(binding: FragmentFinishedBooksBinding?) {
        viewModel.bookToRemove.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeFinishedBook(it)
                viewModel.onBookFinishedBookRemoved()
            }
        })
    }


}
