package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG_FAVORITE_FILMLIST = "FteFilmListFragment"

class FavoriteFilmListFragment: Fragment() {

    private lateinit var favoriteFilmRecyclerView : RecyclerView

    private var favoriteAdapter : FavoriteFilmAdapter? = null

    private val favoriteFilmListViewModel : FavoriteFilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteFilmListViewModel::class.java)
    }

    private val filmListViewModel : FilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_FAVORITE_FILMLIST, "Total films: ${favoriteFilmListViewModel.favoritefilmList.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite_film_list, container,false)
        favoriteFilmRecyclerView = view.findViewById(R.id.favorite_film_recycler_view) as RecyclerView
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            favoriteFilmRecyclerView.layoutManager = GridLayoutManager(context, 3)
        }
        else {
            favoriteFilmRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        updateUI()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private inner class FavoriteFilmHolder (view:View) : RecyclerView.ViewHolder(view) {

        private lateinit var favoriteFilm : FilmsItem

        private val imageFavoriteFilmView : ImageView = itemView.findViewById(R.id.favorite_image_film)
        private val nameFavoriteFilmView : TextView = itemView.findViewById(R.id.favorite_name_film)
        private val buttonDetailsFavoriteView : Button = itemView.findViewById(R.id.favorie_button_details)
        private val buttonRemoveFromFavorite : Button = itemView.findViewById(R.id.button_remove_from_favorite)

        init {
            buttonDetailsFavoriteView.setOnClickListener {
                parentFragmentManager.setFragmentResult("result", Bundle().apply {
                    putInt("name", favoriteFilm.nameFilmId)
                    putInt("description", favoriteFilm.descriptionFilmId)
                    putInt("image", favoriteFilm.imageId)
                })
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DetailsFragment())
                    .addToBackStack("string")
                    .commit()
            }
            buttonRemoveFromFavorite.setOnClickListener {
                favoriteFilm.isFavorite = false
                favoriteAdapter?.notifyDataSetChanged()
            }
        }

        @SuppressLint("ResourceType")
        fun bind (favoriteFilm : FilmsItem) {
            this.favoriteFilm = favoriteFilm
            imageFavoriteFilmView.setImageResource(favoriteFilm.imageId)
            nameFavoriteFilmView.text = resources.getString(favoriteFilm.nameFilmId)
        }

    }

    private inner class FavoriteFilmAdapter (var favoriteFilms : List<FilmsItem>) : RecyclerView.Adapter<FavoriteFilmHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFilmHolder {
            val view = layoutInflater.inflate(R.layout.favorite_item_films, parent, false)
            return FavoriteFilmHolder(view)
        }

        override fun onBindViewHolder(holder: FavoriteFilmHolder, position: Int) {
            val favoriteFilm = favoriteFilms[position]
            holder.bind(favoriteFilm)
        }

        override fun getItemCount(): Int {
            return favoriteFilms.size
        }

    }

    private fun updateUI() {
        for (i in filmListViewModel.filmList) {
            if (i.isFavorite) {
                favoriteFilmListViewModel.favoritefilmList.add(i)
            }
        }
        favoriteAdapter = FavoriteFilmAdapter(favoriteFilmListViewModel.favoritefilmList)
        favoriteFilmRecyclerView.adapter = favoriteAdapter
    }

    companion object {
        fun newInstance() : FavoriteFilmListFragment{
            return FavoriteFilmListFragment()
        }
    }
}