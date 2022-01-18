package com.example.person_vehicle

class BD_Persona {
    companion object{
        val arregloPersonas = arrayListOf<Obj_Persona>()


        init {
            arregloPersonas
                .add(
                    Obj_Persona("0","Sergio", "Jimenez", 23,"Masculino")
                )
            arregloPersonas
                .add(
                    Obj_Persona("1","Yadira", "Rojas", 23,"Femenino")
                )

        }
    }
}