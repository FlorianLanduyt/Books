package com.example.books.domain.about


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.books.R

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    /**
     * Called when the fragment needs to be inflated
     *
     * @param inflater the layoutInflater
     * @param container the viewgroup
     * @param savedInstanceState the bundle created in onSaveInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }


}
