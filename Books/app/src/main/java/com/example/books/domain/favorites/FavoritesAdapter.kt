package com.example.books.domain.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.data.favorites.BookFavorite
import com.example.books.databinding.BookFavoritesListItemBinding

class FavoritesAdapter
    (private val onClickListener: FavoritesAdapter.FavoriteListener)
    : ListAdapter<BookFavorite, FavoritesAdapter.FavoritesBookViewHolder>(DiffCallBack) {

    class FavoritesBookViewHolder(private var binding: BookFavoritesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteBook: BookFavorite, clickListener: FavoriteListener) {
            binding.favoriteBook = favoriteBook
            binding.clickListener = clickListener
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<BookFavorite>() {
        override fun areItemsTheSame(oldItem: BookFavorite, newItem: BookFavorite): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookFavorite, newItem: BookFavorite): Boolean {
            return oldItem.bookId == newItem.bookId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesBookViewHolder {
        return FavoritesBookViewHolder(BookFavoritesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        //return SearchBooksViewHolder(SearchBooksItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: FavoritesBookViewHolder, position: Int) {
        val favoriteBook = getItem(position)

//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(favoriteBook)
//        }

        holder.bind(favoriteBook, onClickListener)
    }

    class FavoriteListener(val clickListener: (favoriteBook: BookFavorite, action: String) -> Unit) {
        fun onClick(favoriteBook: BookFavorite, action: String) = clickListener(favoriteBook, action)
    }
}