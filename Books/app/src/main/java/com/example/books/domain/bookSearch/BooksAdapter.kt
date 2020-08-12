package com.example.books.domain.bookSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.SearchBooksItemBinding
import com.example.books.domain.models.Book

class BooksAdapter(private val onClickListener: OnClickListener) : ListAdapter<Book, BooksAdapter.SearchBooksViewHolder>(DiffCallBack) {
    class SearchBooksViewHolder(private var binding: SearchBooksItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, clickListener: OnClickListener) {
            binding.book = book
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchBooksViewHolder {
        return SearchBooksViewHolder(SearchBooksItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchBooksViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, onClickListener)
    }

    class  OnClickListener(val clickListener: (book: Book, action: String) -> Unit) {
        fun onClick(book: Book, action: String) = clickListener(book, action)
    }
}