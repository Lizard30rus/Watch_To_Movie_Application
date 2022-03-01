package com.example.whatch_to_movie_application.viewmodels

import androidx.lifecycle.ViewModel
import com.example.whatch_to_movie_application.FilmRepository

class FavoriteFilmsViewModel: ViewModel() {

    private val filmRepository = FilmRepository.get()
    val favoriteFilmListLiveData =  filmRepository.getFavoriteFilms(true)
}