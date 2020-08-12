package com.example.books.domain.bookSearch


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
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
class SearchBooksFragment : Fragment()
    , CoroutineScope
{
    override val coroutineContext: CoroutineContext = Dispatchers.Main


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

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SearchBooksViewModel::class.java)
        toReadViewModel =
            ViewModelProviders.of(this, toReadViewModelFactory).get(ToReadViewModel::class.java)
        finishedViewModel = ViewModelProviders.of(this, finishedBookViewModelFactory)
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

        navigateToSelectedBook(viewModel, binding)
        observeBookAddedToToReadList(toReadViewModel)
        observeBookRemovedToToReadList(toReadViewModel)
        observeBookAddedToFinishedList(finishedViewModel)
        observeBookRemovedFinishedList(finishedViewModel)
        observeSearchTextChanged(viewModel, binding)
        observeSearchFieldClicked(viewModel)


        setHasOptionsMenu(true)
        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeSearchFieldClicked(viewModel: SearchBooksViewModel) {
        viewModel.editFieldClicked.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.booksPhotosGrid.visibility = View.INVISIBLE
                } else {
                    binding.booksPhotosGrid.visibility = View.VISIBLE
                }
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeSearchTextChanged(
        viewModel: SearchBooksViewModel,
        binding: FragmentSearchBooksBinding?
    ) {
        binding!!.booksPhotosGrid.visibility = View.VISIBLE
        binding!!.searchText.addTextChangedListener(object: TextWatcher{

            private var searchFor = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val search = s.toString().trim()
                if(search == searchFor)
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


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookAddedToFinishedList(
        viewModel: FinishedBooksViewModel
    ) {
        viewModel.finishedBookAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        R.string.added_to_finished_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                viewModel.onFinishedBookAdded()
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookRemovedFinishedList(
        viewModel: FinishedBooksViewModel
    ) {
        viewModel.finishedBookRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        R.string.removed_from_finished_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                viewModel.onBookFinishedBookRemoved()
            }
        })
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookAddedToToReadList(
        viewModel: ToReadViewModel
    ) {
        viewModel.toReadAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        R.string.added_to_read_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.onToReadBookAdded()
                }
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBookRemovedToToReadList(
        viewModel: ToReadViewModel
    ) {
        viewModel.toReadRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        R.string.removed_to_read_books,
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.onToReadRemoved()
                }
            }
        })
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun navigateToSelectedBook(
        viewModel: SearchBooksViewModel,
        binding: FragmentSearchBooksBinding
    ) {
        viewModel.navigateToSelectedBook.observe(this, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(SearchBooksFragmentDirections.actionShowDetails(it.id!!))
                viewModel.displayBookDetailsComplete()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            binding.searchText.text.toString(),
            when (item.itemId) {
                R.id.show_ebooks_menu -> BookApiFilter.SHOW_E_BOOKS
                R.id.aboutFragment -> BookApiFilter.SHOW_FREE_EBOOKS
                else -> BookApiFilter.SHOW_ALL
            }
        )
        return true
    }
}
