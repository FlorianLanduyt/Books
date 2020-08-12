package com.example.books.data.converters

import androidx.room.TypeConverter
import com.example.books.domain.bookSearch.models.VolumeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VolumeInfoConverter {

    /**
     * Converts a json to a VolumeInfo
     *
     * @param data the json format of VolumeInfo
     * @return the VolumeInfo of a book
     */
    @TypeConverter
    fun fromString(data: String): VolumeInfo? {
        val gson = Gson()
        val type = object : TypeToken<VolumeInfo>() {}.type
        return gson.fromJson(data, type)
    }

    /**
     * Converts VolumeInfo to json
     *
     * @param volumeInfo the volume info of a book
     * @return a json of the volumeinfo
     */
    @TypeConverter
    fun toString(volumeInfo: VolumeInfo): String? {
        val gson = Gson()
        val type = object : TypeToken<VolumeInfo>() {}.type

        return gson.toJson(volumeInfo, type)
    }
}