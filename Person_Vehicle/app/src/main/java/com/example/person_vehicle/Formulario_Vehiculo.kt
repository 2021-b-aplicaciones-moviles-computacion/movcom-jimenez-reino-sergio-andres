package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class Formulario_Vehiculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_vehiculo)

        val txt_placa = findViewById<EditText>(R.id.txt_placa)
        val txt_tipo = findViewById<EditText>(R.id.txt_tipo)
        val txt_color = findViewById<EditText>(R.id.txt_color)
        val txt_llantas = findViewById<EditText>(R.id.txt_numero_llantas)
        val txt_avaluo = findViewById<EditText>(R.id.txt_avaluo)
        val rbg_estado = findViewById<RadioGroup>(R.id.rdg_estado)
        val rbg_motorizado = findViewById<RadioGroup>(R.id.rdg_motorizado)



        val txt_persona_id = intent.getStringExtra("persona_id")

        /*
       txt_id.setText(intent.getStringExtra("id"))
       txt_nombre.setText(intent.getStringExtra("nombre"))
       txt_apellido.setText(intent.getStringExtra("apellido"))
       txt_edad.setText(edad.toString())
       val txt_sexo = intent.getStringExtra("sexo")
       Log.i("Sexo", txt_sexo.toString())

       if(txt_sexo=="Masculino"){
           rbg_sexo.check(R.id.rdb_masculino)
       }
       if(txt_sexo=="Femenino"){
           rbg_sexo.check(R.id.rdb_femenino)
       }
       if(txt_sexo=="Otro"){
           rbg_sexo.check(R.id.rdb_otro)
       }

       if(edad!=0){
           txt_id.isEnabled = false
       }

       */
        val botonGuardar = findViewById<Button>(R.id.btn_registrar_vehiculo)



        botonGuardar.setOnClickListener{
            val radioButton_Idmotorizado = rbg_motorizado.checkedRadioButtonId
            val txt_motorizado = findViewById<RadioButton>(radioButton_Idmotorizado)
            val radioButton_Idestado = rbg_estado.checkedRadioButtonId
            val txt_estado = findViewById<RadioButton>(radioButton_Idmotorizado)
            val intentDevolverPersona = Intent()
            intentDevolverPersona.putExtra("placa",txt_placa.text.toString())
            intentDevolverPersona.putExtra("tipo",txt_tipo.text.toString())
            intentDevolverPersona.putExtra("color",txt_color.text.toString())
            intentDevolverPersona.putExtra("numero_llantas",txt_llantas.text.toString())
            intentDevolverPersona.putExtra("avaluo",txt_avaluo.text.toString())
            intentDevolverPersona.putExtra("motorizado",txt_motorizado.text.toString())
            intentDevolverPersona.putExtra("estado",txt_estado.text.toString())
            intentDevolverPersona.putExtra("persona_id",txt_persona_id.toString())

            setResult(
                RESULT_OK,
                intentDevolverPersona
            )
            finish()


        }

        val botonSalir = findViewById<Button>(R.id.btn_salir_vehiculo)

        botonSalir.setOnClickListener{
            finish()
        }
    }
}