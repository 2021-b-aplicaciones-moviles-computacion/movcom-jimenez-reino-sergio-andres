package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class Vehiculo : AppCompatActivity() {
    val CREAR_VEHICULO = 0
    val ACTUALIZAR_VEHICULO = 1
    var auxPlaca = ""
    var nombre = ""
    var apellido =""
    var persona_id =""
    val db = Firebase.firestore
    val vehiculos = db.collection("Vehiculo")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<Obj_Vehiculo>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)
        val objPersona:Obj_Persona = intent.getParcelableExtra<Obj_Persona>("Persona")!!
        nombre = objPersona.nombre.toString()
        apellido = objPersona.apellido.toString()
        persona_id = objPersona.id.toString()

        actualizarLista()


        val txt_persona = findViewById<TextView>(R.id.txt_persona)
        txt_persona.setText(nombre+" "+apellido)



        val botonAddVehiculo = findViewById<Button>(R.id.btn_add_vehiculo)
        botonAddVehiculo
            .setOnClickListener {
                idItemSeleccionado=-1
                irActividadParametros(Formulario_Vehiculo::class.java)

            }

        val botonSalir = findViewById<Button>(R.id.btn_return_persona)

        botonSalir.setOnClickListener{
            finish()
        }

    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        intent.putExtra("persona_id",persona_id)
        startActivityForResult(intent, CREAR_VEHICULO)
    }



    fun irActividadParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Firestore
        if (idItemSeleccionado > -1){
            val objVehiculo:Obj_Vehiculo = adaptador!!.getItem(idItemSeleccionado)!!
            intentExplicito.putExtra("id",objVehiculo.id)

            intentExplicito.putExtra("actualizar",true)
        }
        intentExplicito.putExtra("persona_id",persona_id)

        idItemSeleccionado=-1

        startActivityForResult(intentExplicito, ACTUALIZAR_VEHICULO)
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
        inflater.inflate(R.menu.menu_vehiculo, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val objVehiculo:Obj_Vehiculo = adaptador!!.getItem(idItemSeleccionado)!!
        return when(item.itemId){
            R.id.mi_editar_vehiculo ->{
                irActividadParametros(Formulario_Vehiculo::class.java)
                return true
            }

            R.id.mi_eliminar_vehiculo -> {
                //Firestore
                val vehiculo = db.collection("Vehiculo")
                vehiculo.document("${objVehiculo.id}").delete()
                    .addOnSuccessListener {
                        Log.i("Mensaje","Vehiculo eliminado")
                    }
                    .addOnFailureListener{
                        Log.i("Error","Fallo al eliminar vehiculo")
                    }

                actualizarLista()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun actualizarLista(){
        val listVehiculo = findViewById<ListView>(R.id.tv_lista_vehiculos)
        val vehiculosPersona = vehiculos.whereEqualTo("persona_id","${persona_id}")
        Log.i("vehiculos",vehiculosPersona.toString())
        vehiculosPersona.get().addOnSuccessListener {
                result ->
            var listaVehiculos = arrayListOf<Obj_Vehiculo>()

            for (document in result){
                listaVehiculos.add(
                    Obj_Vehiculo(
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
                )

            }

            adaptador = ArrayAdapter(
                this, //Contexto
                android.R.layout.simple_expandable_list_item_1, //Como se va a ver en el XML
                listaVehiculos //Arreglo
            )

            listVehiculo.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(listVehiculo)
        }.addOnFailureListener{
            Log.i("Error", "Fallo al obtener vehiculos")
        }



    }


}