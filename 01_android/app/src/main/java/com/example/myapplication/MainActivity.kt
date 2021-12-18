package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button


class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var resultLauncher = registerForActivityResult(
        ActivityResultConstrast.StartActivityForResult()
    ){
        result ->
        if (result.resultCode == RESULT_OK){
            if(result.data != null){
                val data = result.data
                Log.i("intent","${data?.getStringExtra("nombreModificado")}")
                Log.i("intent","${data?.getStringExtra("edadModificado",0)}")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCicloVida = findViewById<Button>(R.id.btn_ir_cliclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }


        val botonlistView = findViewById<Button>(R.id.btn_ir_list_view)
        botonlistView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirAbrirConParametros(CIntentExplicitoParametros::class.java)
            }

    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirAbrirConParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametreos (Solamente variables primitivas)
        intentExplicito.putExtra("nombre","Sergio")
        intentExplicito.putExtra("apellido","Jimenez")
        intentExplicito.putExtra("edad",23)
        resultLauncher.launch(intentExplicito)
    }


}