package com.example.books.domain.toRead


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.books.R
import com.example.books.databinding.FragmentToReadBinding
import com.example.books.domain.bookDetails.BookDetailsViewModel
import com.example.books.domain.bookDetails.BookDetailsViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class ToReadFragment : Fragment() {
    private lateinit var viewModel: ToReadViewModel
    private lateinit var detailsViewModel: BookDetailsViewModel
    private lateinit var binding: FragmentToReadBinding

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
        // Inflate the layout for this fragment
        binding = FragmentToReadBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val viewModelFactory =ToReadViewModelFactory(application)
        val detailsViewModelFactory = BookDetailsViewModelFactory(application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ToReadViewModel::class.java)
        detailsViewModel = ViewModelProviders.of(this, detailsViewModelFactory).get(BookDetailsViewModel::class.java)

        viewModel.getToReads()

        binding.toReadList.adapter = ToReadAdapter(
            ToReadAdapter.ToReadListener { book, action ->
                when(action) {
                    "details" -> viewModel.navigateToBook(book.bookId)
                    "remove" -> viewModel.onBookToReadRemovedClicked(book.bookId)
                }
            }
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeToRead(binding)
        observeRemovedToRead()
        observeNavigateToBook(viewModel)


        return binding.root

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeNavigateToBook(viewModel: ToReadViewModel) {
        viewModel.bookToNavigateTo.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    ToReadFragmentDirections.actionToReadFragmentToBookDetailFragment(it)
                )
                viewModel.navigateToBookFinished()
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeToRead(binding: FragmentToReadBinding) {
        viewModel.toReadBooks.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isEmpty()) {
                    binding.statusOfToReadList.visibility = View.VISIBLE
                    binding.statusOfToReadList.text = resources.getText(R.string.emptyToReadList)
                } else {
                    binding.statusOfToReadList.visibility = View.GONE
                }
            }
        })
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeRemovedToRead() {
        viewModel.removeToReadBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.removeBookToRead(it)
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
