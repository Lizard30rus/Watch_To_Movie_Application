package com.example.whatch_to_movie_application

import android.app.Application
import com.example.whatch_to_movie_application.data.l.FilmsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmIntentApplication : Application() {
    lateinit var filmsApi : FilmsApi

    override fun onCreate() {
        super.onCreate()
        instance = this
        //FilmRepository.initialize(this)
        initRetrofit()
    }

    private fun initRetrofit() {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .build()
                val response = chain.proceed(
                    chain.request()
                        .newBuilder()
                        .url(url)
                        .addHeader("Authorization", "Bearer fdkghgegnin")
                        .build()
                )
//                response.code() == 403 -> Authoriazation Screen
                return@addInterceptor response
            }
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        filmsApi = retrofit.create(FilmsApi::class.java)
    }


    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
        lateinit var instance: FilmIntentApplication
            private set
    }
}