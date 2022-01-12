package com.example.person_vehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Vehiculo : AppCompatActivity() {
    val CREAR_VEHICULO = 0
    val ACTUALIZAR_VEHICULO = 1
    var idItemSeleccionado = 0
    var nombre = ""
    var apellido =""
    var persona_id =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)
        nombre = intent.getStringExtra("nombre").toString()
        apellido = intent.getStringExtra("apellido").toString()
        persona_id = intent.getStringExtra("id").toString()

        val txt_persona = findViewById<TextView>(R.id.txt_persona)
        txt_persona.setText(nombre+" "+apellido)



        val botonAddVehiculo = findViewById<Button>(R.id.btn_add_vehiculo)
        botonAddVehiculo
            .setOnClickListener {
                irActividad(Formulario_Vehiculo::class.java)

            }
    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        intent.putExtra("persona_id",persona_id)
        startActivityForResult(intent, CREAR_VEHICULO)
    }

    fun irActividadParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametreos (Solamente variables primitivas)

        val objVehiculo:Obj_Vehiculo = BD_Vehiculo.arregloVehiculos[idItemSeleccionado]
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
        val listVehiculos= findViewById<ListView>(R.id.tv_lista_vehiculos)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //Como se va a ver en el XML
            BD_Vehiculo.arregloVehiculos //Arreglo
        )
        listVehiculos.adapter = adaptador
        adaptador.notifyDataSetChanged()
        when (requestCode){
            CREAR_VEHICULO ->{
                if (resultCode == RESULT_OK){

                    "placa"
                    "tipo"
                    "color"
                    "numero_llantas"
                    "avaluo"
                    "motorizado"
                    "estado"
                    "persona_id"

                    val placa= "${data?.getStringExtra("placa")}"
                    val tipo= "${data?.getStringExtra("tipo")}"
                    val color="${data?.getStringExtra("color")}"
                    val numero_llantas= "${data?.getStringExtra("numero_llantas")}"
                    val avaluo = "${data?.getStringExtra("avaluo")}"
                    val motorizado = "${data?.getStringExtra("motorizado")}"
                    val estado = "${data?.getStringExtra("estado")}"
                    val persona_id = "${data?.getStringExtra("persona_id")}"

                    BD_Vehiculo.arregloVehiculos.add(Obj_Vehiculo(placa,tipo,color,numero_llantas.toInt(),avaluo.toDouble(),motorizado,estado,persona_id))
                    adaptador.notifyDataSetChanged()
                    registerForContextMenu(listVehiculos)
                }
            }
            ACTUALIZAR_VEHICULO ->{
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
                            registerForContextMenu(listVehiculos)
                        }
                        index++
                    }


                }
            }

        }


    }


}