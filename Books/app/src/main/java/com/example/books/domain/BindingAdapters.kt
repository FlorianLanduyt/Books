package com.example.books.domain

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.books.R
import com.example.books.data.favorites.BookFavorite
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.data.toread.BookToRead
import com.example.books.domain.bookSearch.BooksAdapter
import com.example.books.domain.bookSearch.MyBooksApiStatus
import com.example.books.domain.models.Book
import com.example.books.domain.favorites.FavoritesAdapter
import com.example.books.domain.finishedbooks.FinishedBookAdapter
import com.example.books.domain.toRead.ToReadAdapter
import com.example.books.domain.yourbooks.YourBooksStatus


/**
 * Uses Glide library to load an image by URL into an [ImageView]
 */
class BindingAdapters {
    companion object {


        @BindingAdapter("imageUrl")
        @JvmStatic
                /**
                 * Downloads imageUri with [Glide]
                 * Glide will set the imageUri in the imageView
                 *
                 * @param imgView The imagview to be bound to the URI
                 * @param thumbnail the URI
                 */
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
                /**
                 * Maniulating the data to
                 *
                 * @param recyclerView The ui-element where the adapter can be bound to
                 * @param data The list of data that will be passed in the bindingadapter method
                 */
        fun bindingRecycleView(recyclerView: RecyclerView, data: List<Book>?) {
            val adapter = recyclerView.adapter as BooksAdapter
            adapter.submitList(data)
        }

        @BindingAdapter("favoritesListData")
        @JvmStatic
                /**
                 * Maniulating the data to
                 *
                 * @param recyclerView The ui-element where the adapter can be bound to
                 * @param data The list of data that will be passed in the bindingadapter method
                 */
        fun bindingRecycleViewFavorites(recyclerView: RecyclerView, data: List<BookFavorite>?) {
            val adapter = recyclerView.adapter as FavoritesAdapter
            adapter.submitList(data)
        }


        @BindingAdapter("toReadListData")
        @JvmStatic
                /**
                 * Maniulating the data to
                 *
                 * @param recyclerView The ui-element where the adapter can be bound to
                 * @param data The list of data that will be passed in the bindingadapter method
                 */
        fun bindingRecycleViewToRead(recyclerView: RecyclerView, data: List<BookToRead>?) {
            val adapter = recyclerView.adapter as ToReadAdapter
            adapter.submitList(data)
        }

        @BindingAdapter("finishedBooksListData")
        @JvmStatic
                /**
                 * Maniulating the data to
                 *
                 * @param recyclerView The ui-element where the adapter can be bound to
                 * @param data The list of data that will be passed in the bindingadapter method
                 */
        fun bindingRecycleViewFinishedBooks(recyclerView: RecyclerView, data: List<FinishedBook>?) {
            val adapter = recyclerView.adapter as FinishedBookAdapter
            adapter.submitList(data)
        }


        @BindingAdapter("bookApiStatus")
        @JvmStatic
                /**
                 * Binding the status of the book to image of the status
                 *
                 * @param statusImageView the imageview
                 * @param status the status of the list
                 */
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

                MyBooksApiStatus.EMPTY -> {
                    statusImageView.visibility = View.VISIBLE

                    statusImageView.setImageResource(R.drawable.book)
                }
            }
        }

//        @BindingAdapter("bookListStatus")
//        @JvmStatic
//                /**
//                 * Binding the status of the book to image of the status
//                 *
//                 * @param statusImageView the imageview
//                 * @param status the status of the list
//                 */
//        fun bindListStatus(statusImageView: ImageView, status: YourBooksStatus?) {
//            when (status) {
//                YourBooksStatus.LOADING -> {
//                    statusImageView.visibility = View.VISIBLE
//                    statusImageView.setImageResource(R.drawable.ic_loading_animation)
//                }
//                YourBooksStatus.ERROR -> {
//                    statusImageView.visibility = View.VISIBLE
//                    statusImageView.setImageResource(R.drawable.ic_connection_erro)
//                }
//                YourBooksStatus.DONE -> {
//                    statusImageView.visibility = View.GONE
//                }
//
//                YourBooksStatus.EMPTY -> {
//                    statusImageView.visibility = View.VISIBLE
//                    statusImageView.setImageResource(R.drawable.sad)
//                }
//            }
//        }


//        @BindingAdapter("bookListStatusText")
//        @JvmStatic
//                /**
//                 * Binding the status of the book to image of the status
//                 *
//                 * @param statusImageView the imageview
//                 * @param status the status of the list
//                 */
//        fun bindListStatusText(statusText: TextView, status: YourBooksStatus?) {
//            when (status) {
//                YourBooksStatus.LOADING -> {
//                    statusText.text = "Laden"
//                }
//                YourBooksStatus.ERROR -> {
//                    statusText.text = "Error"
//                }
//                YourBooksStatus.DONE -> {
//                    statusText.visibility = View.GONE
//                }
//
//                YourBooksStatus.EMPTY -> {
//                    statusText.text = "De lijst is leeg, probeer eens wat boeken toe te voegen! "
//                }
//            }
//        }

//        @BindingAdapter("generalListStatus")
//        @JvmStatic
//        fun bindListStatus(statusImageView: ImageView, status: YourBooksStatus) {
//            when (status) {
//                YourBooksStatus.EMPTY -> {
//                    statusImageView.visibility = View.VISIBLE
//                    statusImageView.setImageResource(R.drawable.unhappy_icon)
//                }
//                MyBooksApiStatus.ERROR -> {
//                    statusImageView.visibility = View.VISIBLE
//                    statusImageView.setImageResource(R.drawable.ic_connection_erro)
//                }
//                MyBooksApiStatus.DONE -> {
//                    statusImageView.visibility = View.GONE
//                }
//
//                MyBooksApiStatus.EMPTY -> {
//                    statusImageView.visibility = View.VISIBLE
//
//                    statusImageView.setImageResource(R.drawable.book)
//                }
//            }
//        }


        @BindingAdapter("bookApiStatusText")
        @JvmStatic
                /**
                 * Binding the status of the book to text of the status
                 *
                 * @param textView the textview
                 * @param status the status of the list
                 */
        fun bindStatusText(textView: TextView, status: MyBooksApiStatus) {
            when (status) {
                MyBooksApiStatus.ERROR -> {
                    textView.visibility = View.VISIBLE
                    textView.text = "Sorry er is iets mis gelopen"
                }
                MyBooksApiStatus.DONE -> {
                    textView.visibility = View.GONE
                }

                MyBooksApiStatus.EMPTY -> {
                    textView.visibility = View.VISIBLE
                    textView.text = "Welkom bij Books!"
                }
                else -> {
                    textView.visibility = View.GONE
                }
            }
        }

        /**
         * Binding the authors to a string
         *
         * @param textView the textview
         * @param authors The authors
         */
        @BindingAdapter("authors")
        @JvmStatic
        fun bindAuthors(textView: TextView, authors: List<String>?) {

            val sb: StringBuilder = StringBuilder()

            authors?.let {
                it.forEach {
                    sb.append(it)
                    sb.append(", ")
                }
            }

            if (sb.isNotEmpty()) {
                sb.delete(sb.lastIndex - 1, sb.lastIndex)
            }


            textView.text = sb.toString()
        }

    }
}
