package com.example.whatch_to_movie_application

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FilmsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameFilm : TextView = itemView.findViewById(R.id.nameFilm)
    private val buttonDetails : Button = itemView.findViewById(R.id.buttonDetails)
    private val imageFilm : ImageView = itemView.findViewById(R.id.imageFilm)

    fun bind (item : FilmsItem) {

        nameFilm.text = item.nameFilm
        buttonDetails.text = item.buttonId.toString()
        imageFilm.setImageResource(item.imageId)

    }
}