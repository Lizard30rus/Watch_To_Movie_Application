package com.example.whatch_to_movie_application

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView


class FilmsItemAdapter(private val filmList: List<FilmsItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class FilmsItemViewHolder(filmsItem : View) : RecyclerView.ViewHolder(filmsItem) {

        private val nameFilm : TextView = filmsItem.findViewById(R.id.nameFilm)
        private val imageFilm : ImageView = filmsItem.findViewById(R.id.imageFilm)

        fun bind (filmsItem : FilmsItem) {
            nameFilm.text = filmsItem.nameFilm
            imageFilm.setImageResource(filmsItem.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: $viewType")
        val view = LayoutInflater.from(parent.context)
        return FilmsItemViewHolder(view.inflate(R.layout.item_films, parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        when (holder) {
            is FilmsItemViewHolder -> {
                holder.bind(filmList[position])
            }
        }
        //holder.bind(filmList[position])
    }

    override fun getItemCount(): Int = filmList.size


}