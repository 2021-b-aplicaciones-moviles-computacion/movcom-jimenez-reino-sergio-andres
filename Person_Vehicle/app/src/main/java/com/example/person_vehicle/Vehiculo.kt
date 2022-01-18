package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.ArrayList

class Vehiculo : AppCompatActivity() {
    val CREAR_VEHICULO = 0
    val ACTUALIZAR_VEHICULO = 1
    var idItemSeleccionado = 0
    var auxPlaca = ""
    var nombre = ""
    var apellido =""
    var persona_id =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)
        nombre = intent.getStringExtra("nombre").toString()
        apellido = intent.getStringExtra("apellido").toString()
        persona_id = intent.getStringExtra("id").toString()

        cargarLista()


        val txt_persona = findViewById<TextView>(R.id.txt_persona)
        txt_persona.setText(nombre+" "+apellido)



        val botonAddVehiculo = findViewById<Button>(R.id.btn_add_vehiculo)
        botonAddVehiculo
            .setOnClickListener {
                irActividad(Formulario_Vehiculo::class.java)

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

    fun cargarLista():ArrayList<Obj_Vehiculo>{
        val arregloVehiculos = ArrayList<Obj_Vehiculo>()

        for (value in BD_Vehiculo.arregloVehiculos){
            if (value.persona_id == persona_id){
                arregloVehiculos.add(value)
            }
        }
        val listVehiculos= findViewById<ListView>(R.id.tv_lista_vehiculos)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Como se va a ver en el XML
            arregloVehiculos //Arreglo
        )
        listVehiculos.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listVehiculos)

        Log.i("context-menu","Posicion: ${listVehiculos.onItemSelectedListener.toString()}")
        return arregloVehiculos
    }

    fun irActividadParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametreos (Solamente variables primitivas)
        val auxArrVehiculos = cargarLista()
        val objVehiculo:Obj_Vehiculo = auxArrVehiculos[idItemSeleccionado]
        intentExplicito.putExtra("placa",objVehiculo.placa)
        intentExplicito.putExtra("tipo",objVehiculo.tipo)
        intentExplicito.putExtra("color",objVehiculo.color)
        intentExplicito.putExtra("numero_llantas",objVehiculo.numero_llantas)
        intentExplicito.putExtra("avaluo",objVehiculo.avaluo)
        intentExplicito.putExtra("motorizado",objVehiculo.motorizado)
        intentExplicito.putExtra("estado",objVehiculo.estado)
        intentExplicito.putExtra("persona_id",objVehiculo.persona_id)

        startActivityForResult(intentExplicito, ACTUALIZAR_VEHICULO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            CREAR_VEHICULO ->{
                if (resultCode == RESULT_OK){

                    val placa= "${data?.getStringExtra("placa")}"
                    val tipo= "${data?.getStringExtra("tipo")}"
                    val color="${data?.getStringExtra("color")}"
                    val numero_llantas= "${data?.getStringExtra("numero_llantas")}"
                    val avaluo = "${data?.getStringExtra("avaluo")}"
                    val motorizado = "${data?.getStringExtra("motorizado")}"
                    val estado = "${data?.getStringExtra("estado")}"
                    val persona_id = "${data?.getStringExtra("persona_id")}"

                    BD_Vehiculo.arregloVehiculos.add(Obj_Vehiculo(placa,tipo,color,numero_llantas.toInt(),avaluo.toDouble(),motorizado,estado,persona_id))
                    cargarLista()

                }
            }
            ACTUALIZAR_VEHICULO ->{
                if (resultCode == RESULT_OK){

                    val placa= "${data?.getStringExtra("placa")}"
                    val tipo= "${data?.getStringExtra("tipo")}"
                    val color="${data?.getStringExtra("color")}"
                    val numero_llantas= "${data?.getStringExtra("numero_llantas")}"
                    val avaluo = "${data?.getStringExtra("avaluo")}"
                    val motorizado = "${data?.getStringExtra("motorizado")}"
                    val estado = "${data?.getStringExtra("estado")}"
                    val persona_id = "${data?.getStringExtra("persona_id")}"

                    val arregloVehiculo =  BD_Vehiculo.arregloVehiculos
                    var index = 0
                    for ( value in arregloVehiculo ){
                        if (value.placa ==placa){
                            BD_Vehiculo.arregloVehiculos.set(index,Obj_Vehiculo(placa,tipo,color,numero_llantas.toInt(),avaluo.toDouble(),motorizado,estado,persona_id))
                            cargarLista()
                            break
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
        inflater.inflate(R.menu.menu_vehiculo, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.mi_editar_vehiculo ->{
                irActividadParametros(Formulario_Vehiculo::class.java)
                cargarLista()
                return true
            }

            R.id.mi_eliminar_vehiculo -> {
                Log.i("Lista","item: ${idItemSeleccionado}")
                val auxArrVehiculo =  cargarLista()
                val auxVehiculo = auxArrVehiculo.get(idItemSeleccionado)
                Log.i("Vehiculo",auxVehiculo.toString())
                BD_Vehiculo.arregloVehiculos.remove(auxVehiculo)
                cargarLista()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


}