package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilmsAdapter (private val favoriteList : List<FilmsItem>,
                            private val listener_2 : FavoriteClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    class FavoriteFilmsViewHolder(filmsItem : View) : RecyclerView.ViewHolder(filmsItem) {

        private val nameFilm : TextView = filmsItem.findViewById(R.id.FnameFilm)
        private val imageFilm : ImageView = filmsItem.findViewById(R.id.FimageFilm)
        private val buttonDetails : Button = filmsItem.findViewById(R.id.FbuttonDetails)


        @SuppressLint("ResourceAsColor")
        fun bind (filmsItem: FilmsItem, listener: FavoriteClickListener) {
            nameFilm.text = filmsItem.nameFilm
            imageFilm.setImageResource(filmsItem.imageId)

            buttonDetails.setOnClickListener {
                nameFilm.setTextColor(R.color.click)
                listener.onDetailsClick(filmsItem)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: $viewType")
        val view = LayoutInflater.from(parent.context)
        return FavoriteFilmsViewHolder(view.inflate(R.layout.favorite_item_films, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")

        when (holder) {
            is FavoriteFilmsViewHolder -> {
                holder.bind(favoriteList[position], listener_2)
            }
        }
    }

    override fun getItemCount(): Int = favoriteList.size

        interface  FavoriteClickListener {
            fun onDetailsClick (filmItem : FilmsItem)
        }
}