package com.example.books.domain.finishedbooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.databinding.FinishedBooksListItemBinding

class FinishedBookAdapter (private val onClickListener: FinishedBookAdapter.FinishedBookListener) : ListAdapter<FinishedBook, FinishedBookAdapter.FinishedBookViewHolder>(FinishedBookDiffCallBack) {
    class FinishedBookViewHolder(private var binding: FinishedBooksListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(finishedBook: FinishedBook){
            binding.finishedBook = finishedBook
//            binding.clickListener = clickListener
        }
    }

    class FinishedBookListener(val clickListener: (finishedBook: FinishedBook) -> Unit) {
        fun onClick(finishedBook: FinishedBook) = clickListener(finishedBook)

    }

    companion object FinishedBookDiffCallBack: DiffUtil.ItemCallback<FinishedBook>() {
        override fun areItemsTheSame(oldItem: FinishedBook, newItem: FinishedBook): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FinishedBook, newItem: FinishedBook): Boolean {
            return oldItem.bookId == newItem.bookId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedBookViewHolder {
        return FinishedBookViewHolder(
            FinishedBooksListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FinishedBookViewHolder, position: Int) {
        val finishedBook = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(finishedBook)
        }


        holder.bind(finishedBook)
    }
}


