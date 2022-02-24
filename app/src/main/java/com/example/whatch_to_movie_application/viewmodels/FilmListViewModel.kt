package com.example.whatch_to_movie_application.viewmodels


import androidx.lifecycle.ViewModel
import com.example.whatch_to_movie_application.FilmRepository


open class FilmListViewModel : ViewModel() {

    private val filmRepository = FilmRepository.get()
    val filmListLiveData =  filmRepository.getFilms()

}