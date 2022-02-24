package com.example.whatch_to_movie_application.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.whatch_to_movie_application.FilmsItem

@Dao
interface FilmDAO {

    //Функции записи в базу данных
    @Insert
    fun addFilm(film: FilmsItem)

    @Insert
    fun addFilms (filmList : List<FilmsItem>)

    //Функции запросов к базе данных
    @Query("SELECT * From filmsItem")
    fun getFilms() : LiveData<List<FilmsItem>>

    @Query("SELECT * FROM filmsItem WHERE nameFilmId=(:id)")
    fun getFilm(id: Int) : LiveData<FilmsItem?>

    //Функции обновления базы данных
    @Update
    fun updateFilm(film :FilmsItem)

}