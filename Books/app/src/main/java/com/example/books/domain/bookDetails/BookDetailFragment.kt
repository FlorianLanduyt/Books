package com.example.books.domain.bookDetails


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.books.R
import com.example.books.databinding.FragmentDetailBookBinding
import com.example.books.domain.favorites.FavoritesViewModel
import com.example.books.domain.favorites.FavoritesViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class BookDetailFragment : Fragment() {

    private lateinit var detailViewModel: BookDetailsViewModel
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FragmentDetailBookBinding


    /**
     * Gets called by Android when the fragment should be inflated
     *
     * @param inflater the layout inflater
     * @param container the container
     * @param savedInstanceState the bundle created in onSaveInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_book, container, false)


        val application = requireNotNull(activity).application
        val binding = FragmentDetailBookBinding.inflate(inflater)

        val bookDetailsViewModelFactory = BookDetailsViewModelFactory(application)
        val favoritesViewModelFactory = FavoritesViewModelFactory(application)

        detailViewModel = ViewModelProviders.of(this, bookDetailsViewModelFactory)
            .get(BookDetailsViewModel::class.java)
        favoritesViewModel = ViewModelProviders.of(this, favoritesViewModelFactory)
            .get(FavoritesViewModel::class.java)

        val args = BookDetailFragmentArgs.fromBundle(arguments!!).bookId
        detailViewModel.getBookProperties(args)
        favoritesViewModel.bookInFavorites(args)


        observeBook(binding)
        observeFavoriteAdded(binding)
        observerFavoriteRemoved(binding)
        observeMoreText(binding)
        observeStatus(binding)


        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel
        binding.viewModelFavorites = favoritesViewModel


        return binding.root
    }


    /**
     *  Observes the book and sets the description
     *
     *  @param binding the FragmentDetailBookBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeBook(binding: FragmentDetailBookBinding) {
        detailViewModel.selectedBook.observe(viewLifecycleOwner, Observer {
            it?.let { book ->

                    binding.book = book

                    if (book.volumeInfo?.description != null) {
                        if (book.volumeInfo.description.length > 100) {
                            binding.descriptionText.text =
                                book.volumeInfo.description.subSequence(
                                    0,
                                    100
                                ).toString().plus(" ...")
                        } else {
                            binding.descriptionText.text =
                                book.volumeInfo.description.toString()
                            binding.moreText.visibility = View.GONE
                        }
                    } else {
                        binding.descriptionText.text =
                            resources.getText(R.string.no_description_found)
                        binding.moreText.visibility = View.GONE
                    }

            }
        })
    }

    /**
     *  Observes the need of showing the full description or just a sequence of it
     *
     *  @param binding the FragmentDetailBookBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeMoreText(binding: FragmentDetailBookBinding) {
        detailViewModel.moreText.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.descriptionText.text = binding.book?.volumeInfo?.description!!.toString()
                    binding.moreText.visibility = View.GONE
                    binding.lessText.visibility = View.VISIBLE
                } else {
                    binding.descriptionText.text = binding.book?.volumeInfo?.description!!.subSequence(
                        0,
                        100
                    ).toString().plus(" ...")
                    binding.moreText.visibility = View.VISIBLE
                    binding.lessText.visibility = View.GONE
                }

            }
        })
    }


    /**
     *  Observes if the book is added into favorites and shows the appropriate image
     *
     *  @param binding the FragmentDetailBookBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeFavoriteAdded(binding: FragmentDetailBookBinding?) {
        favoritesViewModel.favoriteAdded.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.buttonAddToFavorites?.setImageResource(R.drawable.favorite_in_list)
                    favoritesViewModel.onFavoriteAdded()
                }
            }
        })
    }

    /**
     *  Observes if the book should be removed out of the favorites
     *
     *  @param binding the FragmentDetailBookBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observerFavoriteRemoved(binding: FragmentDetailBookBinding?) {
        favoritesViewModel.favoriteRemoved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.buttonAddToFavorites?.setImageResource(R.drawable.favorite)
                    favoritesViewModel.onFavoriteRemoved()
                }
            }
        })
    }

    /**
     *  Observes the status of the list and shows the appropriate views
     *
     *  @param binding the FragmentDetailBookBinding
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeStatus(binding: FragmentDetailBookBinding){
        detailViewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it) {
                    BookApiStatus.LOADING -> {
                        binding.authorsLabel.visibility = View.GONE
                        binding.descriptionLabel.visibility = View.GONE
                        binding.loadingImg.visibility = View.VISIBLE
                        binding.moreText.visibility = View.GONE
                        binding.buttonAddToFavorites.visibility = View.GONE
                    }
                    BookApiStatus.DONE -> {

                        binding.authorsLabel.visibility = View.VISIBLE
                        binding.descriptionLabel.visibility = View.VISIBLE
                        binding.bookDetailLayout.visibility = View.VISIBLE
                        binding.moreText.visibility = View.VISIBLE
                        binding.buttonAddToFavorites.visibility = View.VISIBLE
                        binding.loadingImg.visibility = View.GONE
                    }
                    else -> {
                        binding.authorsLabel.visibility = View.VISIBLE
                        binding.descriptionLabel.visibility = View.VISIBLE
                        binding.bookDetailLayout.visibility = View.VISIBLE
                        binding.moreText.visibility = View.VISIBLE
                        binding.buttonAddToFavorites.visibility = View.VISIBLE
                        binding.loadingImg.visibility = View.GONE
                    }
                }

            }
        })
    }

    private fun shareBook() {
        startActivity(getShareIntent())
    }


    /**
     * Creates a shareIntent
     */
    private fun getShareIntent(): Intent {
        val cover: ImageView =
            binding.bookDetailCover

        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"

            putExtra(
                Intent.EXTRA_TEXT,
                "Zeker de moeite om " + detailViewModel.selectedBook.value!!.volumeInfo!!.title + " van "+ detailViewModel.selectedBook.value!!.volumeInfo!!.authors!![0] + " uit te checken op Books!"
            )

        }, "Een topboek")
        return shareIntent
    }


    /**
     * Calls method share book
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.share -> shareBook()
        }
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }



    /**
     * Sets the layoutfile to the menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }



}



