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
        fun bind(toReadBook: BookToRead) {
            binding.toReadBook = toReadBook
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<BookToRead>() {
        override fun areItemsTheSame(oldItem: BookToRead, newItem: BookToRead): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookToRead, newItem: BookToRead): Boolean {
            return oldItem.bookId == newItem.bookId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToReadBookViewHolder {
        return ToReadBookViewHolder(
            BookToReadListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ToReadBookViewHolder, position: Int) {
        val bookToRead = getItem(position)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(bookToRead)
        }

        holder.bind(bookToRead)
    }

    class ToReadListener(val clickListener: (bookToRead: BookToRead) -> Unit) {
        fun onClick(bookToRead: BookToRead) = clickListener(bookToRead)
    }


}