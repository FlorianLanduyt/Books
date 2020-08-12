package com.example.books.domain.models

import android.os.Parcel
import android.os.Parcelable

data class ImageLink(
    val thumbnail: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageLink> {
        override fun createFromParcel(parcel: Parcel): ImageLink {
            return ImageLink(parcel)
        }

        override fun newArray(size: Int): Array<ImageLink?> {
            return arrayOfNulls(size)
        }
    }
}