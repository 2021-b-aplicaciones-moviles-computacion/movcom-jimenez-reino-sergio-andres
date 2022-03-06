package com.example.youtube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerViewVideos = findViewById<RecyclerView>(
            R.id.rv_videos
        )
        inicializarRecyclerView(
            DB_Video.arrVideos,
            MainActivity(),
            recyclerViewVideos
        )
        val botonYoutube = findViewById<ImageView>(R.id.img_YouTube)
        botonYoutube.setOnClickListener {
            irActivity(0)
        }
    }



    fun inicializarRecyclerView(
        lista:List<Video>,
        actividad:MainActivity,
        recyclerView:RecyclerView
    ){
        val adaptador = Adapter_Video(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun irActivity(position:Int){
        val intentExplicito = Intent(this, Player_Video::class.java)
        val video: Video = DB_Video.arrVideos[position]
        intentExplicito.putExtra("name", video.name)
        intentExplicito.putExtra("views", video.views)
        intentExplicito.putExtra("date", video.date)
        intentExplicito.putExtra("tags", "tags")
        intentExplicito.putExtra("channel", video.channel)
        intentExplicito.putExtra("urlVideo", video.urlVideo)
        startActivity(intentExplicito)
    }


}