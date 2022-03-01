package com.example.whatch_to_movie_application

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsItem(@PrimaryKey var nameFilmId: Int= 0,
                     var imageId: Int = Color.BLUE,
                     var descriptionFilmId: Int = 0,
                     var isFavorite : Boolean = false)
