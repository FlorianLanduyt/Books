package com.example.books.domain.bookSearch.models

import android.os.Parcel
import android.os.Parcelable

data class SalesInfo(
    val isEbook: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isEbook) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SalesInfo> {
        override fun createFromParcel(parcel: Parcel): SalesInfo {
            return SalesInfo(parcel)
        }

        override fun newArray(size: Int): Array<SalesInfo?> {
            return arrayOfNulls(size)
        }
    }
}