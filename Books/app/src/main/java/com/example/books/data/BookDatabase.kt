package com.example.books.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.books.data.books.BookDao
import com.example.books.data.books.DatabaseBook
import com.example.books.data.converters.VolumeInfoConverter
import com.example.books.data.favorites.BookFavorite
import com.example.books.data.favorites.FavoritesDao

@Database(entities = [BookFavorite::class, DatabaseBook::class],
    version = 2,
    exportSchema = false)
@TypeConverters(VolumeInfoConverter::class)
abstract class BookDatabase : RoomDatabase(){

    abstract val bookDao: BookDao
    abstract val favoritesDao: FavoritesDao

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