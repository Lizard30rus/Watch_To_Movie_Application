package com.example.whatch_to_movie_application

import java.io.Serializable

data class DescriptionScreen
    (val description : String,
     val imagePath : Int
) : Serializable {
}