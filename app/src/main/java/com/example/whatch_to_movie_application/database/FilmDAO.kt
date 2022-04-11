package com.example.whatch_to_movie_application.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem


@Dao
interface FilmDAO {

    //Функции записи в базу данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: FilmsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilms (filmList : List<FilmsItem>)

    //Функции запросов к базе данных
    @Query("SELECT * From filmsItem")
    fun getFilms() : LiveData<List<FilmsItem>>

    @Query("SELECT * FROM filmsItem WHERE nameFilm=(:id)")
    fun getFilm(id: Int) : LiveData<FilmsItem?>

    @Query("SELECT * FROM filmsitem WHERE isFavorite= (:check)")
    fun getFavoriteFilms(check : Boolean) : LiveData<List<FilmsItem>>


    //Функции обновления базы данных
    @Update
    fun updateFilm(film : FilmsItem)

}
