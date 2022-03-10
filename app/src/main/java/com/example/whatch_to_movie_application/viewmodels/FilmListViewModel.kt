package com.example.whatch_to_movie_application.viewmodels


import androidx.lifecycle.ViewModel
import com.example.whatch_to_movie_application.FilmRepository
import com.example.whatch_to_movie_application.FilmsItem


open class FilmListViewModel : ViewModel() {

    private val filmRepository = FilmRepository.get()
    val filmListLiveData =  filmRepository.getFilms()

    fun addFilms(films : List<FilmsItem>) {
        filmRepository.addFilms(films)
    }

    fun addFilm(film : FilmsItem) {
        filmRepository.addFilm(film)
    }
}