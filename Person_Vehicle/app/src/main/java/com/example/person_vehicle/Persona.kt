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

class Persona : AppCompatActivity() {
    val CREAR_PERSONA = 0
    val ACTUALIZAR_PERSONA = 2


    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona)

        val listPersonas = findViewById<ListView>(R.id.tv_lista_personas)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Como se va a ver en el XML
            BD_Persona.arregloPersonas //Arreglo
        )
        listPersonas.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listPersonas)



        val botonAddPersona = findViewById<Button>(R.id.btn_add_persona)
        botonAddPersona
            .setOnClickListener {
                irActividad(Formulario_Persona::class.java)

            }


    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivityForResult(intent, CREAR_PERSONA)
    }

    fun irActividadParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametreos (Solamente variables primitivas)

        val objPersona:Obj_Persona = BD_Persona.arregloPersonas[idItemSeleccionado]
        intentExplicito.putExtra("id",objPersona.id)
        intentExplicito.putExtra("nombre",objPersona.nombre)
        intentExplicito.putExtra("apellido",objPersona.apellido)
        intentExplicito.putExtra("edad",objPersona.edad)
        intentExplicito.putExtra("sexo",objPersona.sexo)
        //resultLauncher.launch(intentExplicito)
        startActivityForResult(intentExplicito, ACTUALIZAR_PERSONA)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val listPersonas = findViewById<ListView>(R.id.tv_lista_personas)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Como se va a ver en el XML
            BD_Persona.arregloPersonas //Arreglo
        )
        listPersonas.adapter = adaptador
        adaptador.notifyDataSetChanged()
        when (requestCode){
            CREAR_PERSONA ->{
                if (resultCode == RESULT_OK){

                    val id= "${data?.getStringExtra("id")}"
                    val nombre= "${data?.getStringExtra("nombre")}"
                    val apellido="${data?.getStringExtra("apellido")}"
                    val edad= "${data?.getStringExtra("edad")}"
                    val sexo = "${data?.getStringExtra("sexo")}"

                    BD_Persona.arregloPersonas.add(Obj_Persona(id,nombre,apellido,edad.toInt(),sexo))
                    adaptador.notifyDataSetChanged()
                    //registerForContextMenu(listPersonas)
                }
            }
            ACTUALIZAR_PERSONA ->{
                if (resultCode == RESULT_OK){

                    val id= "${data?.getStringExtra("id")}"
                    val nombre= "${data?.getStringExtra("nombre")}"
                    val apellido="${data?.getStringExtra("apellido")}"
                    val edad= "${data?.getStringExtra("edad")}"
                    val sexo = "${data?.getStringExtra("sexo")}"

                    val arregloPersona = BD_Persona.arregloPersonas
                    var index = 0
                    for ( value in arregloPersona ){
                        if (value.id ==id){
                            BD_Persona.arregloPersonas.set(index,Obj_Persona(id,nombre,apellido,edad.toInt(),sexo) )
                            adaptador.notifyDataSetChanged()
                            //registerForContextMenu(listPersonas)
                        }
                        index++
                    }


                }
            }

        }


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
        val listPersonas = findViewById<ListView>(R.id.tv_lista_personas)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Como se va a ver en el XML
            BD_Persona.arregloPersonas //Arreglo
        )
        listPersonas.adapter = adaptador
        adaptador.notifyDataSetChanged()
        return when(item.itemId){
            R.id.mi_editar_persona ->{
                irActividadParametros(Formulario_Persona::class.java)
                return true
            }

            R.id.mi_eliminar_persona -> {
                val auxPersona =  BD_Persona.arregloPersonas[idItemSeleccionado]
                val auxArrVehiculos = BD_Vehiculo.arregloVehiculos
                for (value in auxArrVehiculos){
                    if (value.persona_id == auxPersona.id){
                        BD_Vehiculo.arregloVehiculos.remove(value)
                    }
                }
                BD_Persona.arregloPersonas.remove(auxPersona)
                adaptador.notifyDataSetChanged()
                registerForContextMenu(listPersonas)

                return true
            }

            R.id.mi_ver_vehiculos -> {
                Log.i("context-menu", "Ver vehículos: ${idItemSeleccionado}")
                irActividadParametros(Vehiculo::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



}