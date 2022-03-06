package com.example.youtube
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Video (
    val name:String?,
    val views: Int,
    val date:String?,
    val urlVideo:Int,
    val userEmail:String?,
    val channel:String?,
    val likes:Int,
    val dislikes:Int,
    val comentaries: String?,
    val urlImage:Int,
    val urlImgUser:Int
        ): Parcelable {
    override fun toString(): String {
        return "${name}, ${views}, ${date}, ${userEmail}, ${urlVideo}, ${channel}, ${likes}, ${dislikes},${comentaries}"
    }

    constructor(parcel:Parcel): this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    ){

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(name)
        p0?.writeInt(views)
        p0?.writeString(date)
        p0?.writeString(userEmail)
        p0?.writeInt(urlVideo)
        p0?.writeString(channel)
        p0?.writeInt(likes)
        p0?.writeInt(dislikes)
        p0?.writeString(comentaries)
        p0?.writeInt(urlImage)
        p0?.writeInt(urlImgUser)
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }


}