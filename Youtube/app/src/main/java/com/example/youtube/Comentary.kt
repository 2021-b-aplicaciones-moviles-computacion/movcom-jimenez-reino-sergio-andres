package com.example.youtube

import android.os.Parcel
import android.os.Parcelable

class Comentary(
    val id:String,
    val text:String
) : Parcelable {
    override fun toString(): String {
        return "${id}: ${text} "
    }

    constructor(parcel: Parcel): this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ){

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(id)
        p0?.writeString(text)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

