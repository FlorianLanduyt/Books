package com.example.books.domain.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.data.favorites.BookFavorite
import com.example.books.databinding.BookFavoritesListItemBinding

class FavoritesAdapter
    (private val onClickListener: FavoritesAdapter.FavoriteListener) :
    ListAdapter<BookFavorite, FavoritesAdapter.FavoritesBookViewHolder>(DiffCallBack) {

    class FavoritesBookViewHolder(private var binding: BookFavoritesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * What the viewholder will bind to
         *
         * @param favoriteBook the favorite book to be binded
         * @param clickListener the clicklistener to be binded
         */
        fun bind(favoriteBook: BookFavorite, clickListener: FavoriteListener) {
            binding.favoriteBook = favoriteBook
            binding.clickListener = clickListener
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<BookFavorite>() {

        /**
         * Checks if the items are the same
         *
         * @param oldItem the old FavoriteBook item
         * @param newItem the new FavoriteBook item
         * @return a boolean if the items are the same
         */
        override fun areItemsTheSame(oldItem: BookFavorite, newItem: BookFavorite): Boolean {
            return oldItem === newItem
        }

        /**
         * Checks if the content of the Book items are the same
         *
         * @param oldItem the old FavoriteBook item
         * @param newItem the new FavoriteBook item
         *
         * @return a boolean if the items are the same
         */
        override fun areContentsTheSame(oldItem: BookFavorite, newItem: BookFavorite): Boolean {
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesBookViewHolder {
        return FavoritesBookViewHolder(
            BookFavoritesListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    /**
     * Old view is recycled and reused by binding new data the view when scrolling
     *
     * @param holder the viewholder
     * @param position the position of the viewholder
     */
    override fun onBindViewHolder(holder: FavoritesBookViewHolder, position: Int) {
        val favoriteBook = getItem(position)
        holder.bind(favoriteBook, onClickListener)
    }

    class FavoriteListener(val clickListener: (favoriteBook: BookFavorite, action: String) -> Unit) {

        /**
         * The clicklistener to be passed trough
         *
         * @param book the book where on the clicklistener is called
         * @param action the kind of clicklistener is called
         */
        fun onClick(favoriteBook: BookFavorite, action: String) =
            clickListener(favoriteBook, action)
    }
}