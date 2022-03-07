package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val id = intent.getStringExtra("id")

        val txt_persona_id = intent.getStringExtra("persona_id")

        val actualizar:Boolean = intent.getBooleanExtra("actualizar",false)


        if (actualizar) {
            //val objVehiculo: Obj_Vehiculo = intent.getParcelableExtra<Obj_Vehiculo>("Vehiculo")!!

            val db = Firebase.firestore
            val referencia = db.collection("Vehiculo").document("${id}")
            referencia.get().addOnSuccessListener { document ->
                if(document != null){

                    var objVehiculo:Obj_Vehiculo =Obj_Vehiculo(
                        document.id.toString(),
                        document.get("placa").toString(),
                        document.get("tipo").toString(),
                        document.get("color").toString(),
                        document.get("numero_llantas").toString().toInt(),
                        document.get("avaluo").toString().toDouble(),
                        document.get("motorizado").toString(),
                        document.get("estado").toString(),
                        document.get("persona_id").toString()
                    )

                    txt_placa.setText(objVehiculo.placa)
                    txt_tipo.setText(objVehiculo.tipo)
                    txt_color.setText(objVehiculo.color)
                    txt_llantas.setText(objVehiculo.numero_llantas.toString())
                    txt_avaluo.setText(objVehiculo.avaluo.toString())
                    val txt_motorizado = objVehiculo.motorizado
                    val txt_estado = objVehiculo.estado

                    if (txt_motorizado == "Si") {
                        rbg_motorizado.check(R.id.rdb_motorizado_si)
                    }
                    if (txt_motorizado == "No") {
                        rbg_motorizado.check(R.id.rdb_motorizado_no)
                    }
                    if (txt_estado == "Bueno") {
                        rbg_estado.check(R.id.rdb_estado_bueno)
                    }
                    if (txt_estado == "Regular") {
                        rbg_estado.check(R.id.rdb_estado_regular)
                    }
                    if (txt_estado == "Malo") {
                        rbg_estado.check(R.id.rdb_estado_malo)
                    }

                    if (txt_placa.text.toString() != "") {
                        txt_placa.isEnabled = false
                    }
                }
            }
        }


        val botonGuardar = findViewById<Button>(R.id.btn_registrar_vehiculo)



        botonGuardar.setOnClickListener{
            val radioButton_Idmotorizado = rbg_motorizado.checkedRadioButtonId
            val txt_motorizado = findViewById<RadioButton>(radioButton_Idmotorizado)
            val radioButton_Idestado = rbg_estado.checkedRadioButtonId
            val txt_estado = findViewById<RadioButton>(radioButton_Idestado)

            //Firestore
            devolverVehiculo(
                id.toString(),
                txt_placa.text.toString(),
                txt_tipo.text.toString(),
                txt_color.text.toString(),
                txt_llantas.text.toString(),
                txt_avaluo.text.toString(),
                txt_motorizado.text.toString(),
                txt_estado.text.toString(),
                txt_persona_id.toString(),
                actualizar

            )
        }

        val botonSalir = findViewById<Button>(R.id.btn_salir_vehiculo)

        botonSalir.setOnClickListener{
            finish()
        }
    }

    fun devolverVehiculo(
        id:String,
        placa:String,
        tipo:String,
        color:String,
        numero_llantas:String,
        avaluo:String,
        motorizado:String,
        estado:String,
        persona_id:String,
        actualizar:Boolean

    ){
        //Firestore
        val nuevaPersona = hashMapOf<String,Any>(
            "placa" to placa,
            "tipo" to tipo,
            "color" to color,
            "numero_llantas" to numero_llantas.toInt(),
            "avaluo" to avaluo.toDouble(),
            "motorizado" to motorizado,
            "estado" to estado,
            "persona_id" to persona_id
        )
        val db = Firebase.firestore
        val referencia = db.collection("Vehiculo")
        if(!actualizar){
            referencia
                .add(nuevaPersona)
                .addOnSuccessListener {
                    Log.i("Mensaje","Vehiculo guardado")
                }
                .addOnFailureListener {
                    Log.i("Error","No se pudo guardar el Vehiculo")
                }
        }else{
            referencia
                .document("${id}")
                .update(
                    "placa" , placa,
                    "tipo" , tipo,
                    "color" , color,
                    "numero_llantas" , numero_llantas.toInt(),
                    "avaluo" , avaluo.toDouble(),
                    "motorizado" , motorizado,
                    "estado" , estado,
                    "persona_id" , persona_id
                )
                .addOnSuccessListener {
                    Log.i("Mensaje","Vehiculo actualizado")
                }
                .addOnFailureListener {
                    Log.i("Error","No se pudo actualizar el Vehiculo")
                }
        }


        //Local

        val intentDevolverVehiculo= Intent()

        setResult(
            RESULT_OK,
            intentDevolverVehiculo
        )



        finish()
    }
}