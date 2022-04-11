package com.example.whatch_to_movie_application.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.database.FilmRepository


class FilmDetailsViewModel : ViewModel() {

    private val filmRepository = FilmRepository.get()
    private val filmIdLiveData = MutableLiveData<Int>()

    val filmLiveData: LiveData<FilmsItem?> =
        Transformations.switchMap(filmIdLiveData) {nameFilmId ->
            filmRepository.getFilm(nameFilmId)
        }

    fun loadFilm(nameFilmId : Int) {
        filmIdLiveData.value = nameFilmId
    }

    fun saveFilm(film : FilmsItem) {
        filmRepository.updateFilm(film)
    }

}
