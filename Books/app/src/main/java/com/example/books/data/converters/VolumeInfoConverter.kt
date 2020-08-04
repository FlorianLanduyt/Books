package com.example.books.data.converters

import androidx.room.TypeConverter
import com.example.books.domain.bookSearch.models.VolumeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VolumeInfoConverter {
    @TypeConverter
    fun fromString(data: String): VolumeInfo? {
        val gson = Gson()
        val type = object : TypeToken<VolumeInfo>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun toString(volumeInfo: VolumeInfo): String? {
        val gson = Gson()
        val type = object : TypeToken<VolumeInfo>() {}.type

        return gson.toJson(volumeInfo, type)
    }
}