package com.example.whatch_to_movie_application

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FilmsItem(val nameFilm: String, val imageId: Int, val descriptionFilmId: String) : Parcelable
