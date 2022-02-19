package com.example.whatch_to_movie_application

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class FilmsFragment : Fragment() {

    private lateinit var filmsItem: FilmsItem
    private lateinit var imageView: ImageView
    private lateinit var buttonDetails: Button
    private lateinit var isFavorite : CheckBox
    private lateinit var nameFilm : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmsItem = FilmsItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.item_films, container, false)
        imageView = view.findViewById(R.id.imageFilm)
        buttonDetails =view.findViewById(R.id.buttonDetails)
        isFavorite = view.findViewById(R.id.isFavorite)
        nameFilm = view.findViewById(R.id.nameFilm)


        buttonDetails.setOnClickListener {

        }
        return view
    }

    override fun onStart() {
        super.onStart()

        isFavorite.apply {
            setOnCheckedChangeListener { _, isChecked ->
                filmsItem.isFavorite = isChecked
            }
        }
    }
}