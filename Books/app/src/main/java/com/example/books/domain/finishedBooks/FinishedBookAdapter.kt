package com.example.books.domain.finishedbooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.databinding.FinishedBooksListItemBinding

class FinishedBookAdapter(private val onClickListener: FinishedBookAdapter.FinishedBookListener) :
    ListAdapter<FinishedBook, FinishedBookAdapter.FinishedBookViewHolder>(FinishedBookDiffCallBack) {

    class FinishedBookViewHolder(private var binding: FinishedBooksListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * What the viewholder will bind to
         *
         * @param finishedBook the finished book to be binded
         * @param clickListener the clicklistener to be binded
         */
        fun bind(finishedBook: FinishedBook, clickListener: FinishedBookListener) {
            binding.finishedBook = finishedBook
            binding.clickListener = clickListener
        }
    }


    companion object FinishedBookDiffCallBack : DiffUtil.ItemCallback<FinishedBook>() {

        /**
         * Checks if the items are the same
         *
         * @param oldItem the old FinishedBook item
         * @param newItem the new FinishedBook item
         * @return a boolean if the items are the same
         */
        override fun areItemsTheSame(oldItem: FinishedBook, newItem: FinishedBook): Boolean {
            return oldItem === newItem
        }

        /**
         * Checks if the content of the Book items are the same
         *
         * @param oldItem the old FinishedBook item
         * @param newItem the new FinishedBook item
         *
         * @return a boolean if the items are the same
         */
        override fun areContentsTheSame(oldItem: FinishedBook, newItem: FinishedBook): Boolean {
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedBookViewHolder {
        return FinishedBookViewHolder(
            FinishedBooksListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    /**
     * Old view is recycled and reused by binding new data the view when scrolling
     *
     * @param holder the viewholder
     * @param position the position of the viewholder
     */
    override fun onBindViewHolder(holder: FinishedBookViewHolder, position: Int) {
        val finishedBook = getItem(position)
        holder.bind(finishedBook, onClickListener)
    }


    class FinishedBookListener(val clickListener: (finishedBook: FinishedBook, action: String) -> Unit) {

        /**
         * The clicklistener to be passed trough
         *
         * @param finishedBook the finished book where on the clicklistener is called
         * @param action the kind of clicklistener is called
         */
        fun onClick(finishedBook: FinishedBook, action: String) =
            clickListener(finishedBook, action)

    }

}


