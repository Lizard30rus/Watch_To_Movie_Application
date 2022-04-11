package com.example.whatch_to_movie_application.viewmodels


import androidx.lifecycle.ViewModel
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.database.FilmRepository


open class FilmListViewModel : ViewModel() {

    private val filmRepository = FilmRepository.get()
    val filmListLiveData =  filmRepository.getFilms()

    fun addFilms(films : List<FilmsItem>) {
        filmRepository.addFilms(films)
    }
}
