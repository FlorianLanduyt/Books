package com.example.books.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    @TypeConverter
    fun fromString(data: String): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun toString(list: List<String>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type

        return gson.toJson(list, type)
    }
}