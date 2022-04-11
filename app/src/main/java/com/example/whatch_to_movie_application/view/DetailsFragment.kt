package com.example.whatch_to_movie_application.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.R
import com.example.whatch_to_movie_application.viewmodels.FilmDetailsViewModel

private const val TAG = "Details fragment"


class DetailsFragment: Fragment() {

    private lateinit var filmsItem: FilmsItem
    private lateinit var imageView: ImageView
    private lateinit var descriptionFilm: TextView
    private val filmDetailsViewModel: FilmDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(FilmDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nameFilmId: Int = arguments?.getInt(DESCRIPTION_FILM) as Int
        Log.d(TAG, "args bundle film ${resources.getString(nameFilmId)}")
        filmDetailsViewModel.loadFilm(nameFilmId)
        setHasOptionsMenu(true)
    }

    @SuppressLint("CutPasteId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Details onCreateView")
        val view = inflater.inflate(R.layout.film_description, container, false)
        imageView = view.findViewById(R.id.description_image_film)
        descriptionFilm = view.findViewById(R.id.description_film)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Details onCreateView")
        filmDetailsViewModel.filmLiveData.observe(
            viewLifecycleOwner,
            { filmsItem ->
                filmsItem?.let {
                    this.filmsItem = filmsItem
                    updateUI()
                }
            }
        )
    }

    @SuppressLint("ResourceType")
    private fun updateUI() {
        imageView.setImageResource(filmsItem.id)
        descriptionFilm.text = filmsItem.descriptionFilm
    }

    companion object {
        const val DESCRIPTION_FILM = "film description"

        fun newInstance(nameFilmId: Int): DetailsFragment {
            val args = Bundle().apply {
                putInt(DESCRIPTION_FILM, nameFilmId)
            }
            return DetailsFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.film_description, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_favorites -> {
                if (filmDetailsViewModel.filmLiveData.value?.isFavorite == true) {
                    Toast.makeText(
                        context?.applicationContext, "Film" +
                                " ${filmDetailsViewModel.filmLiveData.value?.nameFilm}" +
                                " already in Favorites!", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    filmDetailsViewModel.filmLiveData.value?.isFavorite = true
                    Toast.makeText(
                        context?.applicationContext, " Film " +
                                "${filmDetailsViewModel.filmLiveData.value?.nameFilm}" +
                                " add to favorites!", Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
            R.id.remove_favorites -> {
                if (filmDetailsViewModel.filmLiveData.value?.isFavorite == false) {
                    Toast.makeText(
                        context?.applicationContext, "Film" +
                                " ${filmDetailsViewModel.filmLiveData.value?.nameFilm}" +
                                " and so not in Favorites!", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    filmDetailsViewModel.filmLiveData.value?.isFavorite = false
                    Toast.makeText(
                        context?.applicationContext, " Film " +
                                "${filmDetailsViewModel.filmLiveData.value?.nameFilm}" +
                                " remove from favorites!", Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        super.onStop()
        filmDetailsViewModel.saveFilm(filmsItem)
    }

}