package com.example.whatch_to_movie_application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem


@Database(entities = [FilmsItem::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDAO
}
