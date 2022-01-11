package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button


class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402
    /*var resultLauncher = registerForActivityResult(
        ActivityResultConstrast.StartActivityForResult()
    ){
        result ->
        if (result.resultCode == RESULT_OK){
            if(result.data != null){
                val data = result.data
                Log.i("intent","${data?.getStringExtra("nombreModificado")}")
                Log.i("intent","${data?.getStringExtra("edadModificado",0)}")
            }
        }
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //BASE DE DATOS SQLite

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)



        if (EBaseDeDatos.TablaUsuario != null) {
            val idQuemado = 2
            EBaseDeDatos.TablaUsuario?.crearUsuarioFormulario(
                "Sergio",
                "Estudiante de Computacion"
            )
            var consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.actualizarUsuarioFormulario(
                "Sergio",
                "Estudiante de Computacion",
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.eliminarUsuarioFormulario(
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
        }




        val botonCicloVida = findViewById<Button>(R.id.btn_ir_cliclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }


        val botonlistView = findViewById<Button>(R.id.btn_ir_list_view)
        botonlistView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirAbrirConParametros(CIntentExplicitoParametros::class.java)
            }

        val botonImplicito = findViewById<Button>(R.id.btn_intent_implicito)
        botonImplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
            }

    }

    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirAbrirConParametros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //Enviar parametreos (Solamente variables primitivas)
        intentExplicito.putExtra("nombre","Sergio")
        intentExplicito.putExtra("apellido","Jimenez")
        intentExplicito.putExtra("edad",23)
        //resultLauncher.launch(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> { //401
                if (resultCode == RESULT_OK){
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
                if(resultCode == RESULT_CANCELED){
                    Log.i("intent-epn","Cancelado")
                }
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> { //401
                if (resultCode == RESULT_OK){
                    if (data != null){
                        if (data.data != null){
                            val uri:Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null,
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )

                            cursor?.close()
                            Log.i("intent-epn","Telefono ${telefono}")
                        }
                    }
                }
                if(resultCode == RESULT_CANCELED){
                    Log.i("intent-epn","Cancelado")
                }
            }
        }


    }


}