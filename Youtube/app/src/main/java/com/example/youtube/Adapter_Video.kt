package com.example.youtube

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import android.app.Activity
import java.security.AccessController.getContext


class Adapter_Video(
    private val context:MainActivity,
    private val listVideos: List<Video>,
    private val recyclerView: RecyclerView


): RecyclerView.Adapter<Adapter_Video.MyViewHolder>() {
    var idItemSeleccionado = 0
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nameVideoTextView: TextView
        val nameChanelTextView: TextView
        val viewsTextView: TextView
        val datePublishedTextView: TextView
        val optionsImg: ImageView
        val channelImg: ImageView
        val channelMiniature: ImageView
        init {
            nameVideoTextView = view.findViewById(R.id.tv_name_video)
            nameChanelTextView = view.findViewById(R.id.tv_name_channel)
            viewsTextView = view.findViewById(R.id.tv_views)
            datePublishedTextView = view.findViewById(R.id.tv_date_published)
            optionsImg = view.findViewById(R.id.img_options)
            channelImg = view.findViewById(R.id.img_channel)
            channelMiniature = view.findViewById(R.id.img_miniature)

            //Functions
            channelMiniature.setOnClickListener{
                Log.i("Numero de video",getItemCount().toString())
            }

            channelImg.setOnClickListener {
                //Go to Channel
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.adapter_view_video,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = listVideos[position]
        holder.nameVideoTextView.text = video.name
        holder.nameChanelTextView.text = video.channel
        holder.viewsTextView.text = video.views.toString()
        holder.datePublishedTextView.text = video.date
        holder.channelMiniature.setImageResource(video.urlImage)
        holder.channelImg.setImageResource(video.urlImgUser)
        holder.channelMiniature.setOnClickListener {
            Log.i("Context: ",context.toString())
            //context.irActivity(position)
            /*val intent = Intent(context, Player_Video::class.java)
            val video: Video = DB_Video.arrVideos[position]
            intent.putExtra("name", video.name)
            intent.putExtra("views", video.views)
            intent.putExtra("date", video.date)
            intent.putExtra("tags", "tags")
            intent.putExtra("sexo", video.channel)
            context.startActivity(intent)*/
        }
    }


    override fun getItemCount(): Int { // Devolvermos el tamaño del arreglo
        return  listVideos.size
    }



}