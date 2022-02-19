package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener

class DetailsFragment: Fragment() {

    private lateinit var filmsItem: FilmsItem
    private lateinit var imageView : ImageView
    private lateinit var nameFilm : TextView
    private lateinit var descriptionFilm : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmsItem = FilmsItem()
    }

    @SuppressLint("CutPasteId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.film_description, container,false)

        imageView = view.findViewById(R.id.description_image_film)
        descriptionFilm = view.findViewById(R.id.description_film)
        nameFilm = view.findViewById(R.id.description_name_film)

        parentFragmentManager.setFragmentResultListener("result",
        this,
        ){ _, result ->
            imageView.setImageResource(result.getInt("image"))
            descriptionFilm.text = resources.getString(result.getInt("description"))
            nameFilm.text = resources.getString(result.getInt("name"))
        }


        return view
    }



    companion object{
        fun newInstance() : DetailsFragment {
            return DetailsFragment()
        }
    }

}