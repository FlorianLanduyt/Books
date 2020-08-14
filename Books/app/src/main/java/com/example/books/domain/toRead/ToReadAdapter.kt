package com.example.books.domain.toRead

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.data.toread.BookToRead
import com.example.books.databinding.BookToReadListItemBinding
import com.example.books.domain.favorites.FavoritesAdapter
import com.example.books.generated.callback.OnClickListener

class ToReadAdapter
    (private val onClickListener: ToReadAdapter.ToReadListener) :
    ListAdapter<BookToRead, ToReadAdapter.ToReadBookViewHolder>(DiffCallBack) {


    class ToReadBookViewHolder(private var binding: BookToReadListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * What the viewholder will bind to
         *
         * @param toReadBook the BookToRead to be binded
         * @param clickListener the clicklistener to be binded
         */
        fun bind(toReadBook: BookToRead, clickListener: ToReadListener) {
            binding.toReadBook = toReadBook
            binding.clickListener = clickListener
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<BookToRead>() {

        /**
         * Checks if the items are the same
         *
         * @param oldItem the old BookToRead item
         * @param newItem the new BookToRead item
         * @return a boolean if the items are the same
         */
        override fun areItemsTheSame(oldItem: BookToRead, newItem: BookToRead): Boolean {
            return oldItem === newItem
        }

        /**
         * Checks if the content of the Book items are the same
         *
         * @param oldItem the old BookToRead item
         * @param newItem the new BookToRead item
         *
         * @return a boolean if the items are the same
         */
        override fun areContentsTheSame(oldItem: BookToRead, newItem: BookToRead): Boolean {
            return oldItem.bookId == newItem.bookId
        }
    }

    /**
     * Creates the viewholder for the recyclerView
     *
     * @param parent the parent viewgroup
     * @param viewtype the type of the view
     * @return a new viewholder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToReadBookViewHolder {
        return ToReadBookViewHolder(
            BookToReadListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    /**
     * Old view is recycled and reused by binding new data the view when scrolling
     *
     * @param holder the viewholder
     * @param position the position of the viewholder
     */
    override fun onBindViewHolder(holder: ToReadBookViewHolder, position: Int) {
        val bookToRead = getItem(position)
        holder.bind(bookToRead, onClickListener)
    }

    class ToReadListener(val clickListener: (bookToRead: BookToRead, action: String) -> Unit) {

        /**
         * The clicklistener to be passed trough
         *
         * @param bookToRead the BookToRead where on the clicklistener is called
         * @param action the kind of clicklistener is called
         */
        fun onClick(bookToRead: BookToRead, action: String) = clickListener(bookToRead, action)
    }


}