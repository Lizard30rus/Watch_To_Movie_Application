package com.example.whatch_to_movie_application.data.l


import com.example.whatch_to_movie_application.data.l.entity.FilmsItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.*

interface FilmsApi {

    @GET("8d057607-c2c1-41f6-985e-033e145c64b3")
    fun getFilmsFromApi() : Flow<List<FilmsItemModel>>

}