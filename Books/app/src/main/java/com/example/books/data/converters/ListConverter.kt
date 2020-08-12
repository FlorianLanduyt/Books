package com.example.books.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    /**
     * Converts a json to a list of strings
     *
     * @param data the json format of a list
     * @return the list of strings
     */
    @TypeConverter
    fun fromString(data: String): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

    /**
     * Converts list of strings to a json
     *
     * @param list the list of strings
     * @return the json format of a list
     */
    @TypeConverter
    fun toString(list: List<String>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type

        return gson.toJson(list, type)
    }
}