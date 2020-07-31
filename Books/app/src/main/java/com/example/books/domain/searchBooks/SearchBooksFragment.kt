package com.example.books.domain.searchBooks


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.books.databinding.FragmentSearchBooksBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.booksPhotosGrid.adapter = BooksAdapter()
        return binding.root
    }


//    private fun searchBookOnClick(viewModel: SearchBooksViewModel, binding: FragmentSearchBooksBinding){
//        binding.searchBtn.setOnClickListener{
//            viewModel.getBooks("superintelligence")
//        }
//    }


}
