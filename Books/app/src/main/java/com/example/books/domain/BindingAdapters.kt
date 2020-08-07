package com.example.books.domain

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.books.R
import com.example.books.data.favorites.BookFavorite
import com.example.books.domain.bookSearch.BooksAdapter
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.bookSearch.models.Book
import com.example.books.domain.favorites.FavoritesAdapter


/**
 * Uses Glide library to load an image by URL into an [ImageView]
 */
class BindingAdapters {
    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindImage(imgView: ImageView, thumbnail: String?) {
            thumbnail?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgView)
            }
        }


        @BindingAdapter("listData")
        @JvmStatic
        fun bindingRecycleView(recyclerView: RecyclerView, data: List<Book>?) {
            val adapter = recyclerView.adapter as BooksAdapter
            adapter.submitList(data)
        }

        @BindingAdapter("favoritesListData")
        @JvmStatic
        fun bindingRecycleViewFavorites(recyclerView: RecyclerView, data: List<BookFavorite>?){
            val adapter = recyclerView.adapter as FavoritesAdapter
            adapter.submitList(data)
        }


        @BindingAdapter("bookApiStatus")
        @JvmStatic
        fun bindStatus(statusImageView: ImageView, status: MyBooksApiStatus) {
            when (status) {
                MyBooksApiStatus.LOADING -> {
                    statusImageView.visibility = View.VISIBLE
                    statusImageView.setImageResource(R.drawable.ic_loading_animation)
                }
                MyBooksApiStatus.ERROR -> {
                    statusImageView.visibility = View.VISIBLE
                    statusImageView.setImageResource(R.drawable.ic_connection_erro)
                }
                MyBooksApiStatus.DONE -> {
                    statusImageView.visibility = View.GONE
                }
                else -> {
                    statusImageView.visibility = View.GONE
                }

            }
        }

    }
}
