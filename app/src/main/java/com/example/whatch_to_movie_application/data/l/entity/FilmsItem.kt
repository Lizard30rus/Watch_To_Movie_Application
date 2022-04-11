package com.example.whatch_to_movie_application.data.l.entity

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FilmsItem(
    val imageFilm : String,
    @PrimaryKey val id: Int,
    val nameFilm: String,
    val descriptionFilm : String,
    var isFavorite : Boolean = false
)
