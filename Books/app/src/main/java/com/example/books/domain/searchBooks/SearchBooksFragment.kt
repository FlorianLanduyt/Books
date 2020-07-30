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
import com.example.books.databinding.GridViewItemBinding

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
        val binding = GridViewItemBinding.inflate(inflater)

        //val binding: GridViewItemBinding = DataBindingUtil.inflate(inflater, R.layout.grid_view_item,container,false)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        return binding.root
    }


}
