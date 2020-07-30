package com.example.books

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class BindingAdapters {
    companion object {
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun bindImage(imgView: ImageView, thumbnail: String?) {
            thumbnail?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .into(imgView)
            }
        }
    }
}
