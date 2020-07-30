package com.example.books.domain.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.books.R
import com.example.books.databinding.FragmentHomeBinding
import com.example.books.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.searchBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_searchBooksFragment)
        }
        return binding.root
    }


}
