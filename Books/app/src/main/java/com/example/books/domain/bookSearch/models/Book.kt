package com.example.books.domain.bookSearch.models

import android.os.Parcel
import android.os.Parcelable

data class Book(
    val id: String?,
    val volumeInfo: VolumeInfo?,
    val salesInfo: SalesInfo?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(VolumeInfo::class.java.classLoader),
        parcel.readParcelable(SalesInfo::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(volumeInfo, flags)
        parcel.writeParcelable(salesInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}