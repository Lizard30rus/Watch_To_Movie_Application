package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.details_Button_1).setOnClickListener {

            //получение строки из ресурса
            val previewOfNewListTitle: String by lazy { resources.getString(R.string.description_Film_1) }
            val imageResourse = R.drawable.gentl_image
            FilmDescriptionAct.start(this, DescriptionScreen(previewOfNewListTitle , imageResourse))
        }

    }
}