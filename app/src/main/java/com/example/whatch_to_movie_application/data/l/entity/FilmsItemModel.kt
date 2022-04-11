package com.example.whatch_to_movie_application.data.l.entity

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FilmsItemModel(
    @SerializedName("poster_path") val imageFilm : String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val nameFilm: String,
    @SerializedName("description") val descriptionFilm : String
    )
