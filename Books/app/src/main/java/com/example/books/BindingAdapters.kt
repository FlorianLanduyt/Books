package com.example.books

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.books.domain.searchBooks.BooksAdapter
import com.example.books.network.searchBooks.Book


/**
 * Uses Glide library to load an image by URL into an [ImageView]
 */
class BindingAdapters {
    companion object {

        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun bindImage(imgView: ImageView, thumbnail: String?) {
            thumbnail?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                    .into(imgView)
            }
        }


        @BindingAdapter("listData")
        @JvmStatic
        fun bindingRecycleView(recyclerView: RecyclerView, data: List<Book>?){
            val adapter = recyclerView.adapter as BooksAdapter
            adapter.submitList(data)
        }
    }
}
