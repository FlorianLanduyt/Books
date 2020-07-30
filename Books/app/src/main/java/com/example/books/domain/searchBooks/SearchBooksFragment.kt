package com.example.books.domain.searchBooks


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.books.R
import com.example.books.databinding.FragmentSearchBooksBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchBooksFragment : Fragment() {

    private lateinit var binding: FragmentSearchBooksBinding

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
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_search_books,
//            container,
//            false
//        )

        val binding = FragmentSearchBooksBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        return binding.root
    }


}
