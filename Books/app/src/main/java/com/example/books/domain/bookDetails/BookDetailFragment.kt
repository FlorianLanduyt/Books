package com.example.books.domain.bookDetails


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.books.R
import com.example.books.databinding.FragmentDetailBookBinding
import kotlinx.coroutines.supervisorScope

/**
 * A simple [Fragment] subclass.
 */
class BookDetailFragment : Fragment() {
//    private lateinit var viewModel: BookDetailsViewModel
//    private lateinit var binding: FragmentDetailBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBookBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val book = BookDetailFragmentArgs.fromBundle(arguments!!).book

        val viewModelFactory = BookDetailsViewModelFactory(book, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookDetailsViewModel::class.java)



        return binding.root
    }
}



