package com.example.whatch_to_movie_application

import androidx.lifecycle.ViewModel

class FavoriteFilmListViewModel: ViewModel() {

    val favoritefilmList = mutableListOf<FilmsItem>()

}