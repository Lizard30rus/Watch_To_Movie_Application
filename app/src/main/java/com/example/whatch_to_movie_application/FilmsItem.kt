package com.example.whatch_to_movie_application

import android.graphics.Color
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
data class FilmsItem(@PrimaryKey var nameFilmId: Int= 0,
                     var imageId: Int = Color.BLUE,
                     var descriptionFilmId: Int = 0,
                     var isFavorite : Boolean = false
                     )
