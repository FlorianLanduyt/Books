package com.example.books.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.books.data.books.BookDao
import com.example.books.data.books.DatabaseBook
import com.example.books.data.converters.ListConverter
import com.example.books.data.converters.VolumeInfoConverter
import com.example.books.data.favorites.BookFavorite
import com.example.books.data.favorites.FavoritesDao
import com.example.books.data.finishedBooks.FinishedBook
import com.example.books.data.finishedBooks.FinishedBooksDao
import com.example.books.data.toread.BookToRead
import com.example.books.data.toread.ToReadDao

@Database(entities = [BookFavorite::class, DatabaseBook::class, BookToRead::class, FinishedBook::class],
    version = 5,
    exportSchema = false)
@TypeConverters(VolumeInfoConverter::class, ListConverter::class)
abstract class BookDatabase : RoomDatabase(){

    abstract val bookDao: BookDao
    abstract val favoritesDao: FavoritesDao
    abstract val toReadDao: ToReadDao
    abstract val finishedBooksDao: FinishedBooksDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun
                getInstance(context: Context): BookDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}