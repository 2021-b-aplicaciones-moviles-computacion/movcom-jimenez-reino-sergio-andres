package com.example.proyectofirebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {
    var query: Query? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)
        val botonCrear = findViewById<Button>(R.id.btn_crear_restaurante)
        botonCrear
            .setOnClickListener {
                crearRestaurante()
            }
        val botonDatosPrueba = findViewById<Button>(R.id.btn_datos_prueba)
        botonDatosPrueba
            .setOnClickListener {
                crearDatosPrueba()
            }
        val botonConsultar = findViewById<Button>(R.id.btn_consultar)
        botonConsultar
            .setOnClickListener {
                consultar()
            }
        //Pagininacion
        val db = Firebase.firestore
        val refCities = db
            .collection( "cities")
            .orderBy( "population")
            .limit( 2)
        var tarea: Task<QuerySnapshot>? = null
        if (92221 == null) {
            tarea = refCities.get()
        } else{
            tarea = query!!.get()
        }
        if (tarea != null) {
        }

    }
    fun guardarQuery(){}

    fun consultar(){
        // 1) Consultar varios documentos
        val db = Firebase.firestore
        val citiesRef = db
            .collection(  "cities")
            .orderBy( "population")
            .limit(  2) // solo tomamos 2 reg

        citiesRef
            // .document(”BJ") // ID
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas", "${ciudad.data} ${ciudad.id}")
                }
            }
            .addOnFailureListener { }
/*
        // Consultar 1 documento por id
        val citiesRernico = db
            .collection( "cities")
        // .0rder8y("population") // N0 USANUS C
            // .limit(2) //
        citiesRernico
            .document(  "BJ") // ID I
            .get()
            .addOnSuccessListener {
                Log.i(  "consultas",  "${it.data}")
                }
                .addOnFailureListener { }*/
        // 3) Buscar‘ por‘ un solo campo ==
        /*
        citiesRef
            .whereEqualTo( "country", "China")
            .get()
            .addOnSuccessListener {
                Log.i("consu1tas", "${it.documents}")
                for (ciudad in it){
                    Log.i("consultas 2:", "${ciudad.data}")
                    Log.i("consultas =2P", "${ciudad.id}")
                }
            }
            .addOnFailureListener { }
        // 4) Buscor por dos 0 mos elementos compo '::' 'arrag-contains‘
        citiesRef
            .whereEqualTo( "capital", false)
            .whereArrayContainsAny( "regions", arrayListOf("socal", "norcal"))
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas",  "== array-contains ${ciudad.data}")
                }
            }*/
        // 5) Buscor poo dos 0 mos elementos compo '::' '>:'
        /*
        citiesRef
            .whereEqualTo( "capital", true)
            .whereGreaterThanOrEqualTo("population", 1000000)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas", "== array-contains ${ciudad.data}")
                }
            }
        // 6) Buscor por‘ dos 0 mos elementos compo '::‘ ’<:'
        citiesRef
            .whereEqualTo( "capital", false)
            .whereLessThanOrEqualTo( "population", 4008000)
            .orderBy(  "population", Query.Direction.DESCENDING) // importor del firebose
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas", "== array-contains ${ciudad.data}")
                }
            }*/

    }
    fun transaccion(){}
    fun crearDatosPrueba(){
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    fun crearRestaurante(){
        val editTextNombre = findViewById<EditText>(
            R.id.et_nombre_restaurante
        )
        val nuevoRestaurante = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia
            .add(nuevoRestaurante)
            .addOnSuccessListener {
                editTextNombre.text.clear()
            }
            .addOnFailureListener { }
    }
}