package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        Log.i("ciclo_vida", "onCreate")
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                aumentarTotal()
            }



    }

    fun aumentarTotal(){
        total += total + 1
        val textViewCicloVida = findViewById<TextView>(R.id.tv_ciclo_vida)
        textViewCicloVida.text = total.toString()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.run {
            // GUARDAR LAS VARIABLES
            //PRIMITIVOS

            putInt("totalGuardado",total)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val totalRecupedado:Int? = savedInstanceState.getInt("totalGuardado")
        if(totalRecupedado!=null){
            this.total = totalRecupedado
            val txvCicloVida = findViewById<TextView>(R.id.tv_ciclo_vida)
        }
    }

    override fun onStart(){
        super.onStart()
        Log.i("ciclo_vida", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo_vida", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo_vida", "onDestroy")
    }
}