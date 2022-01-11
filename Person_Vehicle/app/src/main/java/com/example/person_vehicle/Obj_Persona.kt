package com.example.person_vehicle

import android.os.Parcel
import android.os.Parcelable

class Obj_Persona (
    val id: String?,
    val nombre: String?,
    val apellido: String?,
    val edad: Int?,
    val sexo: String?
):Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ){

    }

    override fun toString(): String {
        return "${id} : ${nombre} ${apellido} ${edad} ${sexo}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(id)
        p0?.writeString(nombre)
        p0?.writeString(apellido)
        p0?.writeString(edad.toString())
        p0?.writeString(sexo)
    }

    companion object CREATOR : Parcelable.Creator<Obj_Persona> {
        override fun createFromParcel(parcel: Parcel): Obj_Persona {
            return Obj_Persona(parcel)
        }

        override fun newArray(size: Int): Array<Obj_Persona?> {
            return arrayOfNulls(size)
        }
    }

}