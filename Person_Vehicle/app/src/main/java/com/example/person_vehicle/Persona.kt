package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Persona : AppCompatActivity() {
    val CREAR_PERSONA = 0
    val ACTUALIZAR_PERSONA = 2

    val db = Firebase.firestore
    val personas = db.collection("Persona")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<Obj_Persona>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona)

        actualizarLista()

        val botonAddPersona = findViewById<Button>(R.id.btn_add_persona)
        botonAddPersona
            .setOnClickListener {
                irActividadParametros(Formulario_Persona::class.java)
            }


    }

    fun actualizarLista(){
        val listPersonas = findViewById<ListView>(R.id.tv_lista_personas)

        personas.get().addOnSuccessListener {
                result ->
            var listaPersonas = arrayListOf<Obj_Persona>()

            for (document in result){
                listaPersonas.add(
                    Obj_Persona(
                        document.id.toString(),
                        document.get("nombre").toString(),
                        document.get("apellido").toString(),
                        document.get("edad").toString().toInt(),
                        document.get("sexo").toString()
                    )
                )

            }

            adaptador = ArrayAdapter(
                this, //Contexto
                android.R.layout.simple_expandable_list_item_1, //Como se va a ver en el XML
                listaPersonas //Arreglo
            )

            listPersonas.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(listPersonas)
        }.addOnFailureListener{
            Log.i("Error", "Fallo al obtener personas")
        }



    }

    fun irActividadParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)

        //Firestore
        if (idItemSeleccionado > -1){
            val objPersona:Obj_Persona = adaptador!!.getItem(idItemSeleccionado)!!
            intentExplicito.putExtra("Persona",objPersona)
            intentExplicito.putExtra("persona_id",objPersona.id)
            intentExplicito.putExtra("actualizar",true)
        }

        idItemSeleccionado=-1
        startActivityForResult(intentExplicito, ACTUALIZAR_PERSONA)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        actualizarLista()
    }



    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_persona, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu","Posicion: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val objPersona:Obj_Persona = adaptador!!.getItem(idItemSeleccionado)!!
        return when(item.itemId){
            R.id.mi_editar_persona ->{
                irActividadParametros(Formulario_Persona::class.java)
                return true
            }

            R.id.mi_eliminar_persona -> {

                //Firestore
                personas.document("${objPersona.id}").delete()
                    .addOnSuccessListener {
                        Log.i("Mensaje","Persona eliminada")
                    }
                    .addOnFailureListener{
                        Log.i("Error","Fallo al eliminar persona")
                    }

                actualizarLista()
                return true
            }

            R.id.mi_ver_vehiculos -> {
                Log.i("context-menu", "Ver vehículos: ${idItemSeleccionado}")
                irActividadParametros(Vehiculo::class.java)
                idItemSeleccionado=-1
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



}