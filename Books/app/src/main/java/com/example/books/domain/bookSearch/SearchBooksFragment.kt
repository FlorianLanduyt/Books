package com.example.books.domain.bookSearch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.books.databinding.FragmentSearchBooksBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchBooksFragment : Fragment() {

 //   private lateinit var binding: FragmentSearchBooksBinding

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: SearchBooksViewModel by lazy {
        ViewModelProviders.of(this).get(SearchBooksViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBooksBinding.inflate(inflater)

        searchBookOnClick(viewModel, binding)

        binding.setLifecycleOwner(this)

        navigateToSelectedBook(viewModel, binding)

        binding.viewModel = viewModel

        binding.booksPhotosGrid.adapter = BooksAdapter(BooksAdapter.OnClickListener{
            viewModel.displayBookDetails(it)
        })
        return binding.root
    }


    private fun searchBookOnClick(viewModel: SearchBooksViewModel, binding: FragmentSearchBooksBinding){
        binding.searchBtn.setOnClickListener{
            val searchText: String = binding.searchText.text.toString().trim().replace("\\s".toRegex(),"+")

            viewModel.getBooks(searchText)
        }
    }

    private fun navigateToSelectedBook(viewModel: SearchBooksViewModel, binding: FragmentSearchBooksBinding) {
        viewModel.navigateToSelectedBook.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(SearchBooksFragmentDirections.actionShowDetails(it))
                viewModel.displayBookDetailsComplete()
                binding.searchText.text = null
            }
        })
    }


}
