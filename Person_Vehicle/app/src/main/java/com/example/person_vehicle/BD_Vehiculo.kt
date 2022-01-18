package com.example.person_vehicle

class BD_Vehiculo {
    companion object{
        val arregloVehiculos = arrayListOf<Obj_Vehiculo>()
        val subArrVehiculos = arrayListOf<Obj_Vehiculo>()


        init {
            arregloVehiculos
                .add(
                    Obj_Vehiculo("DBZ-100","CSV", "Rojo", 2,15000.00, "Si","Bueno","0")
                )

            arregloVehiculos
                .add(
                    Obj_Vehiculo("ALF-404","Camion", "Azul", 2,30000.00, "Si","Bueno","1")
                )

        }
    }
}

