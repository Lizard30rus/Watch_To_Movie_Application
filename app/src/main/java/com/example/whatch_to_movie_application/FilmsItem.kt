package com.example.whatch_to_movie_application

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmsItem(var nameFilmId: Int= 0,
                     var imageId: Int = Color.BLUE,
                     var descriptionFilmId: Int = 0,
                     var isFavorite : Boolean = false
                     ) : Parcelable
