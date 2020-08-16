package com.example.books.JUnit


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.books.data.BookDatabase
import com.example.books.data.favorites.BookFavorite
import com.example.books.data.favorites.FavoritesDao
import com.example.books.domain.models.Book
import com.example.books.domain.models.ImageLink
import com.example.books.domain.models.VolumeInfo
import java.io.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteTest {

    private lateinit var bookFavoritesDao: FavoritesDao
    private lateinit var database: BookDatabase

    private var book = Book(
        "7_H8AwAAQBAJ",
        VolumeInfo(
            "Superintelligence",
            listOf("Nick Bostrom"),
            "The human brain has some capabilities that the brains of other animals lack. It is to these distinctive capabilities that our species owes its dominant position. " +
                    "Other animals have stronger muscles or sharper claws, but we have cleverer brains. If machine brains one day come to surpass human brains in general intelligence, " +
                    "then this new superintelligence could become very powerful. As the fate of the gorillas now depends more on us humans than on the gorillas themselves, so the fate of " +
                    "our species then would come to depend on the actions of the machine superintelligence. But we have one advantage: we get to make the first move. Will it be possible to" +
                    " construct a seed AI or otherwise to engineer initial conditions so as to make an intelligence explosion survivable? How could one achieve a controlled detonation? To get " +
                    "closer to an answer to this question, we must make our way through a fascinating landscape of topics and considerations. Read the book and learn about oracles, genies, " +
                    "singletons; about boxing methods, tripwires, and mind crime; about humanity's cosmic endowment and differential technological development; indirect normativity, instrumental" +
                    " convergence, whole brain emulation and technology couplings; Malthusian economics and dystopian evolution; artificial intelligence, and biological cognitive enhancement," +
                    " and collective intelligence.",

            ImageLink("http://books.google.com/books/content?id=7_H8AwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"),
            "Paths, Dangers, Strategies",
            "en"
        )

    )


    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        bookFavoritesDao = database.favoritesDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertBookFavorite() {
        val favorite = BookFavorite(
            book.id!!,
            book.volumeInfo!!.title!!,
            book.volumeInfo!!.authors!!
        )

        bookFavoritesDao.insert(favorite)

        val favoriteFromDB = bookFavoritesDao.get(book.id!!)
        Assert.assertEquals(favorite.bookId, favoriteFromDB.bookId)
    }


    @Test
    @Throws(IOException::class)
    fun deleteBookFavorite() {
        val favorite = BookFavorite(
            book.id!!,
            book.volumeInfo!!.title!!,
            book.volumeInfo!!.authors!!
        )

        bookFavoritesDao.insert(favorite)
        val favoriteFromDB = bookFavoritesDao.get(book.id!!)
        Assert.assertEquals(favorite.bookId, favoriteFromDB.bookId)

        bookFavoritesDao.delete(favorite.bookId)

        Assert.assertNull(bookFavoritesDao.get(favorite.bookId))
    }


}
