package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get
import androidx.core.view.iterator
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Formulario_Persona : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_persona)

        val txt_id = findViewById<EditText>(R.id.txt_idPersona)
        val txt_nombre = findViewById<EditText>(R.id.txt_nombre)
        val txt_apellido = findViewById<EditText>(R.id.txt_apellido)
        val txt_edad = findViewById<EditText>(R.id.txt_edad)
        val rbg_sexo = findViewById<RadioGroup>(R.id.rdg_sexo)

        txt_id.isEnabled = false


        //Firestore

        if (intent.getBooleanExtra("actualizar",false)){
            val persona_id = intent.getStringExtra("persona_id")
            val db = Firebase.firestore
            val referencia = db.collection("Persona").document("${persona_id}")

            referencia.get().addOnSuccessListener { document ->

                if (document != null) {
                    //Toast.makeText(this, "${persona_id}", Toast.LENGTH_SHORT).show()
                    var objPersona:Obj_Persona =Obj_Persona(
                        document.id.toString(),
                        document.get("nombre").toString(),
                        document.get("apellido").toString(),
                        document.get("edad").toString().toInt(),
                        document.get("sexo").toString()
                    )

                    txt_id.setText(objPersona.id.toString())
                    txt_nombre.setText(objPersona.nombre.toString())
                    txt_apellido.setText(objPersona.apellido.toString())
                    txt_edad.setText(objPersona.edad.toString())
                    val txt_sexo = objPersona.sexo.toString()

                    if(txt_sexo=="Masculino"){
                        rbg_sexo.check(R.id.rdb_masculino)
                    }
                    if(txt_sexo=="Femenino"){
                        rbg_sexo.check(R.id.rdb_femenino)
                    }
                    if(txt_sexo=="Otro"){
                        rbg_sexo.check(R.id.rdb_otro)
                    }
                }else{
                    Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show()
                }
            }
        }


        val botonGuardar = findViewById<Button>(R.id.btn_registrar_persona)



        botonGuardar.setOnClickListener{
            val radioButton_Id = rbg_sexo.checkedRadioButtonId
            val txt_sexo = findViewById<RadioButton>(radioButton_Id)
            devolverPersona(
                txt_id.text.toString(),
                txt_nombre.text.toString(),
                txt_apellido.text.toString(),
                txt_edad.text.toString(),
                txt_sexo.text.toString(),
            )

        }

        val botonSalir = findViewById<Button>(R.id.btn_salir_persona)

        botonSalir.setOnClickListener{
            finish()
        }


    }


    fun devolverPersona(
        id:String,
        nombre:String,
        apellido:String,
        edad:String,
        sexo:String

    ){
        //Firestore
        val nuevaPersona = hashMapOf<String,Any>(
            "apellido" to apellido,
            "edad" to edad.toInt(),
            "nombre" to nombre,
            "sexo" to sexo
        )
        val db = Firebase.firestore
        val referencia = db.collection("Persona")
        if(id.equals("")){
            referencia
                .add(nuevaPersona)
                .addOnSuccessListener {
                    Log.i("Mensaje","Persona guardada")
                }
                .addOnFailureListener {
                    Log.i("Error","No se pudo guardar la Persona")
                }
        }else{
            referencia
                .document("${id}")
                .update(
                    "apellido",  apellido,
                    "edad",  edad.toInt(),
                    "nombre",  nombre,
                    "sexo",  sexo
                )
                .addOnSuccessListener {
                    Log.i("Mensaje","Persona actualizada")
                }
                .addOnFailureListener {
                    Log.i("Error","No se pudo actualizar la Persona")
                }
        }


        //Local

        val intentDevolverPersona = Intent()

        setResult(
            RESULT_OK,
            intentDevolverPersona
        )



        finish()
    }



}