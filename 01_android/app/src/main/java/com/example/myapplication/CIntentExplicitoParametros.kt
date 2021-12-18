package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getStringExtra("edad")
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        Log.i("intent","valores ${nombre},${apellido},${edad} ${entrenador})" )
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.
                setOnClickListener{devolverRespuesta()}
    }

    fun devolverRespuesta(
    ){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Sergio")
        intentDevolverParametros.putExtra("edadModificado", 33)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()

    }
}