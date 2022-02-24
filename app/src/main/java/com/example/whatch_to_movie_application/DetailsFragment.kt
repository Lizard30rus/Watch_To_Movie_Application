package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whatch_to_movie_application.viewmodels.FilmDetailsViewModel

private const val TAG = "Details fragment"
private const val DESCRIPTION_FILM = "film description"

class DetailsFragment: Fragment() {

    private lateinit var filmsItem: FilmsItem
    private lateinit var imageView : ImageView
    private lateinit var descriptionFilm : TextView
    private lateinit var isFavorite : CheckBox
    private val filmDetailsViewModel: FilmDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(FilmDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmsItem = FilmsItem()
        val nameFilmId: Int = arguments?.getInt(DESCRIPTION_FILM) as Int
        Log.d(TAG, "args bundle film ${resources.getString(nameFilmId)}")
        filmDetailsViewModel.loadFilm(nameFilmId)
    }

    @SuppressLint("CutPasteId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.film_description, container,false)

        imageView = view.findViewById(R.id.description_image_film)
        descriptionFilm = view.findViewById(R.id.description_film)
        isFavorite = view.findViewById(R.id.is_favorite)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmDetailsViewModel.filmLiveData.observe(
            viewLifecycleOwner,
            Observer { filmsItem ->
                filmsItem?.let {
                    this.filmsItem = filmsItem
                    updateUI()
                }
            }
        )
    }

    @SuppressLint("ResourceType")
    private fun updateUI() {
        imageView.setImageResource(filmsItem.imageId)
        descriptionFilm.text = resources.getString(filmsItem.descriptionFilmId)
        isFavorite.isChecked = filmsItem.isFavorite
    }

    companion object {

       fun newInstance(nameFilmId : Int) : DetailsFragment {
           val args = Bundle().apply {
               putInt(DESCRIPTION_FILM, nameFilmId)
           }
           return DetailsFragment().apply {
               arguments = args
           }
       }
    }


    override fun onStop() {
        super.onStop()
        filmDetailsViewModel.saveFilm(filmsItem)
    }

}