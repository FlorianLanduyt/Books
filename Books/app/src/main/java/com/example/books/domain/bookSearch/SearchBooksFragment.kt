package com.example.books.domain.bookSearch


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.books.R

import com.example.books.databinding.FragmentSearchBooksBinding
import com.example.books.domain.finishedbooks.FinishedBooksViewModel
import com.example.books.domain.finishedbooks.FinishedBooksViewModelFactory
import com.example.books.domain.toRead.ToReadViewModel
import com.example.books.domain.toRead.ToReadViewModelFactory
import com.example.books.network.BookApiFilter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass.
 */
class SearchBooksFragment : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main


    private lateinit var binding: FragmentSearchBooksBinding
    private lateinit var viewModel: SearchBooksViewModel
    private lateinit var toReadViewModel: ToReadViewModel
    private lateinit var finishedViewModel: FinishedBooksViewModel


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
        binding = FragmentSearchBooksBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchBookViewModelFactory(application)
        val toReadViewModelFactory = ToReadViewModelFactory(application)
        val finishedBookViewModelFactory = FinishedBooksViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchBooksViewModel::class.java)
        toReadViewModel =
            ViewModelProvider(this, toReadViewModelFactory).get(ToReadViewModel::class.java)
        finishedViewModel = ViewModelProvider(this, finishedBookViewModelFactory)
            .get(FinishedBooksViewModel::class.java)


        binding.booksPhotosGrid.adapter =
            BooksAdapter(BooksAdapter.OnClickListener { book, action ->
                when (action) {
                    "insertFavorites" -> {
                        toReadViewModel.insertBookToRead(book)
                        toReadViewModel.onToReadBookAddClicked()
                    }
                    "details" -> viewModel.displayBookDetails(book)
                    "insertFinished" -> {
                        finishedViewModel.insertFinishedBook(book)
                        finishedViewModel.onFinishedBookAddClicked()
                    }
                }
            })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        navigateToSelectedBook(viewModel)
        observeBookAddedToToReadList(toReadViewModel)
        observeBookRemovedToToReadList(toReadViewModel)
        observeBookAddedToFinishedList(finishedViewModel)
        observeBookRemovedFinishedList(finishedViewModel)
        observeSearchTextChanged(viewModel, binding)


        setHasOptionsMenu(true)
        return binding.root
    }


    /**
     *  Observes the text in the input field
     *
     *  @param viewModel the SearchBookModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeSearchTextChanged(
        viewModel: SearchBooksViewModel,
        binding: FragmentSearchBooksBinding?
    ) {
        binding!!.booksPhotosGrid.visibility = View.VISIBLE
        binding.searchText.addTextChangedListener(object : TextWatcher {

            private var searchFor = ""


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            /**
             * After text changed -> send new request to fetch the books with new query
             */
            override fun afterTextChanged(s: Editable?) {
                val search = s.toString().trim()
                if (search == searchFor)
                    return

                searchFor = search


                launch {
                    delay(400)
                    if (search != searchFor)
                        return@launch

                    viewModel.getBooks(s.toString(), BookApiFilter.SHOW_ALL)
                }
            }
        })
    }


    /**
     * Observes the book added in finished books list to show a snackbar
     *
     * @param viewModel the FinishedBooksViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookAddedToFinishedList(
        viewModel: FinishedBooksViewModel
    ) {
        viewModel.finishedBookAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        R.string.added_to_finished_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                viewModel.onFinishedBookAdded()
            }
        })
    }


    /**
     * Observes the book removed in finished books list to show a snackbar
     *
     * @param viewModel the FinishedBooksViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookRemovedFinishedList(
        viewModel: FinishedBooksViewModel
    ) {
        viewModel.finishedBookRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        R.string.removed_from_finished_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                viewModel.onBookFinishedBookRemoved()
            }
        })
    }


    /**
     * Observes the book added in ToRead list to show a snackbar
     *
     * @param viewModel the ToReadViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookAddedToToReadList(
        viewModel: ToReadViewModel
    ) {
        viewModel.toReadAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        R.string.added_to_read_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.onToReadBookAdded()
                }
            }
        })
    }

    /**
     * Observes the book removed in to read books list to show a snackbar
     *
     * @param viewModel the ToReadViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookRemovedToToReadList(
        viewModel: ToReadViewModel
    ) {
        viewModel.toReadRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        R.string.removed_to_read_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.onToReadRemoved()
                }
            }
        })
    }


    /**
     * Observes the book to be navigated to
     *
     * @param viewModel the ToReadViewModel
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun navigateToSelectedBook(
        viewModel: SearchBooksViewModel
    ) {
        viewModel.navigateToSelectedBook.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(SearchBooksFragmentDirections.actionShowDetails(it.id!!))
                viewModel.displayBookDetailsComplete()
            }
        })
    }


    /**
     * Shows the overflow menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }


    /**
     * prepares the overflow menu
     */
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (binding.searchText.text.isNullOrEmpty()) {
            menu.getItem(0).setEnabled(false)
            menu.getItem(1).setEnabled(false)
            menu.getItem(2).setEnabled(false)
        } else {
            menu.getItem(0).setEnabled(true)
            menu.getItem(1).setEnabled(true)
            menu.getItem(2).setEnabled(true)
        }

    }


    /**
     * When selecting an option of the overflow menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            binding.searchText.text.toString(),
            when (item.itemId) {
                R.id.show_ebooks_menu -> BookApiFilter.SHOW_E_BOOKS
                R.id.show_free_ebooks -> BookApiFilter.SHOW_FREE_EBOOKS
                R.id.show_all_menu -> BookApiFilter.SHOW_ALL
                else -> BookApiFilter.SHOW_ALL
            }
        )
        return (super.onOptionsItemSelected(item));
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearBooks()
    }
}

