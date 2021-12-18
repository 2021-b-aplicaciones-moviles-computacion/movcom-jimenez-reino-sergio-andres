package com.example.myapplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()


        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Sergio","s@s.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente", "b@b.com")
                )
        }
    }
}