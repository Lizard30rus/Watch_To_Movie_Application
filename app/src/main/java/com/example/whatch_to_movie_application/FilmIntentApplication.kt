package com.example.whatch_to_movie_application

import android.app.Application

class FilmIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FilmRepository.initialize(this)
    }
}