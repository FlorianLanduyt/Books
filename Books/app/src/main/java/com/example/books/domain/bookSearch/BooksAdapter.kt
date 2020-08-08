package com.example.books.domain.bookSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.GridViewItemBinding
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.toRead.ToReadViewModel

class BooksAdapter(private val onClickListener: OnClickListener) : ListAdapter<Book, BooksAdapter.SearchBooksViewHolder>(DiffCallBack) {
    class SearchBooksViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()

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
        return SearchBooksViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchBooksViewHolder, position: Int) {
        val book = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(book)
        }
        holder.bind(book)
    }

    class  OnClickListener(val clickListener: (book: Book) -> Unit) {
        fun onClick(book: Book) = clickListener(book)
    }
}