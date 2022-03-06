package com.example.youtube

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView

class Player_Video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_video)

        //TextView
        val nameVideoTextView: TextView = findViewById<TextView>(R.id.tv_name_video_p)
        val viewsTextView: TextView = findViewById<TextView>(R.id.tv_views_p)
        val dateTextView: TextView = findViewById<TextView>(R.id.tv_date_p)
        val tagsTextView: TextView = findViewById<TextView>(R.id.tv_tags_p)
        val channelTextView: TextView = findViewById<TextView>(R.id.tv_name_channel_p)
        val subscriptorsTextView: TextView = findViewById<TextView>(R.id.tv_subscriptors_p)

        //ImageButton
        val likeImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_like_p)
        val dislikeImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_dislike_p)
        val shareImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_share_p)
        val createImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_create_p)
        val downloadImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_download_p)
        val saveImageButton: ImageButton = findViewById<ImageButton>(R.id.ib_save_p)

        //RecyclerView
        val comentariesRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.rv_comentaries)
        val recyclerViewVideos: RecyclerView = findViewById<RecyclerView>(R.id.rv_video_p)

        //VideoView
        val playerVideoView: VideoView = findViewById<VideoView>(R.id.vv_video)

        val data = intent.getStringExtra("name")
        Log.i("Name video", data.toString())

        //Load Values
        nameVideoTextView.setText(intent.getStringExtra("name"))
        viewsTextView.setText(intent.getStringExtra("views"))
        dateTextView.setText(intent.getStringExtra("date"))
        tagsTextView.setText(intent.getStringExtra("tags"))
        channelTextView.setText(intent.getStringExtra("channel"))
        subscriptorsTextView.setText(intent.getStringExtra("subcriptors"))

        //PLayer
        val url:String = "android.resource://"+packageName+"/"+intent.getStringExtra("urlVideo")

        val mediaController:MediaController = MediaController(this)
        mediaController!!.setAnchorView(playerVideoView)
        playerVideoView!!.setMediaController(mediaController)
        playerVideoView!!.setVideoURI(Uri.parse(url))
        playerVideoView!!.requestFocus()
        playerVideoView!!.start()
        playerVideoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext,"Video End", Toast.LENGTH_LONG).show()
        }




        //Recyclers
        inicializarRecyclerView(
            DB_Video.arrVideos,
            MainActivity(),
            recyclerViewVideos
        )

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
    /*
    fun irActividadParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        val video: Video = DB_Video.arrVideos[idItemSeleccionado]
        intentExplicito.putExtra("name", video.name)
        intentExplicito.putExtra("views", video.views)
        intentExplicito.putExtra("date", video.date)
        intentExplicito.putExtra("tags", "tags")
        intentExplicito.putExtra("sexo", video.channel)

        ActivityCompat.startActivityForResult(this, intentExplicito)
    }
    */

}