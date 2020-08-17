package com.example.books.domain.finishedbooks


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.databinding.FragmentFavoritesBinding

import com.example.books.databinding.FragmentFinishedBooksBinding
import com.example.books.domain.toRead.ToReadFragmentDirections

/**
 * A simple [Fragment] subclass.
 */
class FinishedBookFragment : Fragment() {
    private lateinit var viewModel: FinishedBooksViewModel
    private lateinit var binding: FragmentFinishedBooksBinding

    /**
     * Gets called by Android when the fragmnent is inflated
     *
     * @param inflater the layout inflater
     * @param container the container
     * @param savedInstanceState the bundle created in onSaveInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finished_books, container, false)


        val application = requireNotNull(this.activity).application
        val viewModelFactory = FinishedBooksViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FinishedBooksViewModel::class.java)

        viewModel.getFinishedBooks()


        val adapter =
            FinishedBookAdapter(FinishedBookAdapter.FinishedBookListener{ book, action ->
                when(action){
                    "remove" -> viewModel.onFinishedBookRemovedClicked(book.bookId)
                    "details" -> viewModel.onBookFinishedClicked(book.bookId)
                }

            })

        binding.finishedBooksList.adapter = adapter
        binding.finishedBooksList.layoutManager = LinearLayoutManager(this.context)


        setHasOptionsMenu(true)
        observeFinishedBooks(binding)
        observeRemovedToRead()
        observeNavigateToBook(viewModel)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    /**
     * Observes book to be navigated to
     *
     * @param viewModel the FinishedBooksViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeNavigateToBook(viewModel: FinishedBooksViewModel) {
        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    FinishedBookFragmentDirections.actionFinishedBookFragmentToBookDetailFragment(it)
                )
                viewModel.onBookFinishedNavigated()
            }
        })
    }

    /**
     * Observes finished books to check if the list is empty
     *
     * @param binding the FragmentFinishedBooksBinding
     */
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

    /**
     * Observes removed books
     *
     * @param viewModel the FinishedBooksViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedToRead() {
        viewModel.bookToRemove.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeFinishedBook(it)
                viewModel.onBookFinishedBookRemoved()
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
