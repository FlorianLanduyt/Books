package com.example.books.domain.models

import android.os.Parcel
import android.os.Parcelable

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLink?,
    val subtitle: String?,
    val language: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readParcelable(ImageLink::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeStringList(authors)
        parcel.writeString(description)
        parcel.writeParcelable(imageLinks, flags)
        parcel.writeString(subtitle)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VolumeInfo> {
        override fun createFromParcel(parcel: Parcel): VolumeInfo {
            return VolumeInfo(parcel)
        }

        override fun newArray(size: Int): Array<VolumeInfo?> {
            return arrayOfNulls(size)
        }
    }
}