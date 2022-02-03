package com.example.whatch_to_movie_application

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.film_recycler) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }


    private fun init() {


        val filmList =  mutableListOf(
            FilmsItem(resources.getString(R.string.name_Film_1), R.drawable.gentl_image, resources.getString(R.string.description_Film_1)),
            FilmsItem(resources.getString(R.string.name_Film_2), R.drawable.cards_cash_two_guns, resources.getString(R.string.description_Film_2)),
            FilmsItem(resources.getString(R.string.name_Film_3), R.drawable.revolver, resources.getString(R.string.description_Film_3)),
            FilmsItem(resources.getString(R.string.name_Film_4), R.drawable.big_score, resources.getString(R.string.description_Film_4)),
            FilmsItem(resources.getString(R.string.name_Film_5), R.drawable.attraction, resources.getString(R.string.description_Film_5))
        )


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilmsItemAdapter(filmList)

    }


/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE)
        {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "the invitation is sent!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong! :(", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

 */


    private companion object {
        const val REQ_CODE = 234
    }



}