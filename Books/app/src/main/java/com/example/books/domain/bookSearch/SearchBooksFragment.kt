package com.example.books.domain.bookSearch


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.books.R
import com.example.books.data.BookDatabase

import com.example.books.databinding.FragmentSearchBooksBinding
import com.example.books.domain.finishedbooks.FinishedBooksViewModel
import com.example.books.domain.finishedbooks.FinishedBooksViewModelFactory
import com.example.books.domain.toRead.ToReadViewModel
import com.example.books.domain.toRead.ToReadViewModelFactory
import com.example.books.network.BookApiFilter

/**
 * A simple [Fragment] subclass.
 */
class SearchBooksFragment : Fragment() {

    private lateinit var binding: FragmentSearchBooksBinding
    private lateinit var viewModel: SearchBooksViewModel
    private lateinit var toReadViewModel: ToReadViewModel
    private lateinit var finishedViewModel: FinishedBooksViewModel

    /**
     * Lazily initialize our [OverviewViewModel].
     */
//    private val viewModel: SearchBooksViewModel by lazy {
//        ViewModelProviders.of(this).get(SearchBooksViewModel::class.java)
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBooksBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchBookViewModelFactory(application)
        val toReadViewModelFactory = ToReadViewModelFactory(application)
        val finishedBookViewModelFactory = FinishedBooksViewModelFactory(application)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchBooksViewModel::class.java)
        toReadViewModel = ViewModelProviders.of(this, toReadViewModelFactory).get(ToReadViewModel::class.java)
        finishedViewModel = ViewModelProviders.of(this, finishedBookViewModelFactory).get(FinishedBooksViewModel::class.java)


        binding.booksPhotosGrid.adapter =
            BooksAdapter(BooksAdapter.OnClickListener { book, action ->
                when (action) {
                    "insertFavorites" -> {
                        toReadViewModel.insertBookToRead(book)
                    }
                    "details" -> viewModel.displayBookDetails(book)
                    "insertFinished" -> finishedViewModel.insertFinishedBook(book)
                }
            })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        searchBookOnClick(viewModel, binding)
        navigateToSelectedBook(viewModel, binding)

        setHasOptionsMenu(true)
        return binding.root
    }




    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun searchBookOnClick(viewModel: SearchBooksViewModel, binding: FragmentSearchBooksBinding){
        binding.searchBtn.setOnClickListener{
            val searchText: String = binding.searchText.text.toString().trim().replace("\\s".toRegex(),"+")

            viewModel.getBooks(searchText
                    ,BookApiFilter.SHOW_ALL
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun navigateToSelectedBook(viewModel: SearchBooksViewModel, binding: FragmentSearchBooksBinding) {
        viewModel.navigateToSelectedBook.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(SearchBooksFragmentDirections.actionShowDetails(it))
                viewModel.displayBookDetailsComplete()
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookToRead(binding: FragmentSearchBooksBinding) {
        toReadViewModel.toReadAdded.observe(this, Observer {
            if (it) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(binding.searchText.text.toString(),
            when (item.itemId) {
                R.id.show_ebooks_menu -> BookApiFilter.SHOW_E_BOOKS
                R.id.aboutFragment -> BookApiFilter.SHOW_FREE_EBOOKS
                else -> BookApiFilter.SHOW_ALL
            }
        )
        return true
    }
}
