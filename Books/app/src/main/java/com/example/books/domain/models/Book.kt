package com.example.books.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.example.books.data.books.DatabaseBook

data class Book(
    val id: String?,
    val volumeInfo: VolumeInfo?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(VolumeInfo::class.java.classLoader)
    ) {

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(volumeInfo, flags)
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


fun Book.asDatabaseModel(): DatabaseBook {
    return DatabaseBook(
        id!!,
        volumeInfo
    )
}



