package com.example.person_vehicle

import android.os.Parcel
import android.os.Parcelable

class Obj_Vehiculo(
    val placa: String,
    val tipo: String,
    val color: String,
    val numero_llantas: Int,
    val avaluo: Double,
    val motorizado: String,
    val estado: String,
    val persona_id: String
):Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ){

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(placa)
        p0?.writeString(tipo)
        p0?.writeString(color)
        p0?.writeString(numero_llantas.toString())
        p0?.writeDouble(avaluo)
        p0?.writeString(motorizado)
        p0?.writeString(estado)
        p0?.writeString(persona_id)
    }

    companion object CREATOR : Parcelable.Creator<Obj_Vehiculo> {
        override fun createFromParcel(parcel: Parcel): Obj_Vehiculo {
            return Obj_Vehiculo(parcel)
        }

        override fun newArray(size: Int): Array<Obj_Vehiculo?> {
            return arrayOfNulls(size)
        }
    }
}