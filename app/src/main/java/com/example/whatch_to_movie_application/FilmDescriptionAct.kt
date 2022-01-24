package com.example.whatch_to_movie_application

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class FilmDescriptionAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_description)

        val filmImage = findViewById<ImageView>(R.id.image_Film_1)

        val descriptionScreen = intent.getSerializableExtra(DESCRIPTION_FILM) as?
                DescriptionScreen ?: error("Description is not found")
        //описание фильма
        findViewById<TextView>(R.id.description_Film_1).text = descriptionScreen.description
        //картинка фильма
        //val imagePath = descriptionScreen.imagePath
        //filmImage.setImageURI(Uri.parse(imagePath))
        filmImage.setImageResource(descriptionScreen.imagePath)
    }


    companion object {

        const val DESCRIPTION_FILM = "description film"

        fun start (context : Context, descriptionScreen: DescriptionScreen) {

            val intent = Intent(context, FilmDescriptionAct::class.java).apply {

                putExtra(DESCRIPTION_FILM, descriptionScreen)
            }

            context.startActivity(intent)
        }

    }



}






