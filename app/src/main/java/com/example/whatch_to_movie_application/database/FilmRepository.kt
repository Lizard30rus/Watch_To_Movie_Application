package com.example.whatch_to_movie_application.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.database.FilmDatabase
import java.util.concurrent.Executors

private const val DATABASE_NAME = "film_database"


class FilmRepository private constructor(context: Context){

    private val database: FilmDatabase = Room.databaseBuilder(
        context.applicationContext,
        FilmDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val filmDao = database.filmDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getFilms() : LiveData<List<FilmsItem>> = filmDao.getFilms()

    fun getFavoriteFilms(isFavorite : Boolean) : LiveData<List<FilmsItem>> = filmDao.getFavoriteFilms(isFavorite)

    fun getFilm(id: Int) : LiveData<FilmsItem?> = filmDao.getFilm(id)

    fun updateFilm(filmsItem: FilmsItem) {
        executor.execute {
            filmDao.updateFilm(filmsItem)
        }
    }
//Будет использоваться в дальнейшем для добавления фильмов в БД
    fun addFilms(filmsItem: List<FilmsItem>) {
        executor.execute {
            filmDao.addFilms(filmsItem)
        }
    }

    fun addFilm(filmsItem: FilmsItem) {
        executor.execute {
            filmDao.addFilm(filmsItem)
        }
    }


    companion object {
        private var INSTANCE : FilmRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FilmRepository(context)
            }
        }
        fun get(): FilmRepository {
            return INSTANCE ?:
            throw IllegalStateException("FilmRepository must be initialized")
        }
    }
}
