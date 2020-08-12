package com.example.books.domain.bookSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.databinding.SearchBooksItemBinding
import com.example.books.domain.models.Book

class BooksAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Book, BooksAdapter.SearchBooksViewHolder>(DiffCallBack) {

    class SearchBooksViewHolder(private var binding: SearchBooksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * What the viewholder will bind to
         *
         * @param book the book to be binded
         * @param clickListener the clicklistener to be binded
         */
        fun bind(book: Book, clickListener: OnClickListener) {
            binding.book = book
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Book>() {

        /**
         * Checks if the items are the same
         *
         * @param oldItem the old Book item
         * @param newItem the new Book item
         * @return a boolean if the items are the same
         */
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        /**
         * Checks if the content of the Book items are the same
         *
         * @param oldItem the old Book item
         * @param newItem the new Book item
         *
         * @return a boolean if the items are the same
         */
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }
    }


    /**
     * Creates the viewholder for the recyclerView
     *
     * @param parent the parent viewgroup
     * @param viewtype the type of the view
     * @return a new viewholder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchBooksViewHolder {
        return SearchBooksViewHolder(SearchBooksItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Old view is recycled and reused by binding new data the view when scrolling
     *
     * @param holder the viewholder
     * @param position the position of the viewholder
     */
    override fun onBindViewHolder(holder: SearchBooksViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, onClickListener)
    }


    class OnClickListener(val clickListener: (book: Book, action: String) -> Unit) {
        /**
         * The clicklistener to be passed trough
         *
         * @param book the book where on the clicklistener is called
         * @param action the kind of clicklistener is called
         */
        fun onClick(book: Book, action: String) = clickListener(book, action)
    }
}