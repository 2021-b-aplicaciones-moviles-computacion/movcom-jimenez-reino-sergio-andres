package com.example.myapplication

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        var scriptCrearTablaUsuario =
            """
                
            """.trimIndent()

        Log.i("bbd","Creando la tabla de usuario")
        db?.execSQL(scriptCrearTablaUsuario)

    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ):Boolean{
        val baseDatosEscritura = readableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoEscritura: Long = baseDatosEscritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        baseDatosEscritura.close()
        return if (resultadoEscritura.toInt()== 1) false else true
    }


    fun consultarUsuarioPorId(id: Int): EUsuarioBDD{
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        if (existeUsuario){
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if(id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return  usuarioEncontrado
    }

    fun eliminarUsuarioFormulario(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        //Esta es otra forma de ocupar las funciones de la base de datos
        val resultadoEliminacion = conexionEscritura
            .delete(
                "USUARIO",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }


    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre",nombre)
        valoresAActualizar.put("descripcion",descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()== -1) false else true
    }


    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}