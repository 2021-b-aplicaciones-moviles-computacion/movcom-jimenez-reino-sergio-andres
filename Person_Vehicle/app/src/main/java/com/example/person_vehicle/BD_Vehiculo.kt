package com.example.person_vehicle

class BD_Vehiculo {
    companion object{
        val arregloVehiculos = arrayListOf<Obj_Vehiculo>()


        init {
            arregloVehiculos
                .add(
                    Obj_Vehiculo("DBZ-100","CSV", "Rojo", 2,15000.00, "Si","Bueno","000001")
                )

        }
    }
}

