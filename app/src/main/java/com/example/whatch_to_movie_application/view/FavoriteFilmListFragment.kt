package com.example.whatch_to_movie_application.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.R
import com.example.whatch_to_movie_application.viewmodels.FavoriteFilmsViewModel


class FavoriteFilmListFragment: Fragment() {

    interface Callbacks {
        fun onDetailsFromFavoriteFilmSelected(nameFilmId : Int)
    }

    private var callbacks: Callbacks? = null
    private lateinit var favoriteFilmRecyclerView : RecyclerView
    private var favoriteAdapter : FavoriteFilmAdapter? = null

    private val favoriteListViewModel : FavoriteFilmsViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteFilmsViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
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

        return view
    }



 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteListViewModel.favoriteFilmListLiveData.observe(
            viewLifecycleOwner,
            Observer { favoriteFilms ->
                favoriteFilms.let {
                    updateUI(favoriteFilms)
                }
            }
        )
    }



 @SuppressLint("NotifyDataSetChanged")


 private inner class FavoriteFilmHolder (view:View) : RecyclerView.ViewHolder(view) {

        private lateinit var favoriteFilm : FilmsItem

        private val imageFavoriteFilmView : ImageView = itemView.findViewById(R.id.favorite_image_film)
        private val nameFavoriteFilmView : TextView = itemView.findViewById(R.id.favorite_name_film)
        private val buttonDetailsFavoriteView : Button = itemView.findViewById(R.id.favorie_button_details)

     @SuppressLint("ResourceType")
     fun bind (film : FilmsItem)
     {
         nameFavoriteFilmView.text  = film.nameFilm
         buttonDetailsFavoriteView.text = resources.getString(R.string.details)

         Glide.with(imageFavoriteFilmView.context)
             .load(film.imageFilm)
             .placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.android)
             .centerCrop()
             .into(imageFavoriteFilmView)
     }
        init {
            buttonDetailsFavoriteView.setOnClickListener {
                callbacks?.onDetailsFromFavoriteFilmSelected(favoriteFilm.id)
            }
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

 private fun updateUI(favoriteFilms : List<FilmsItem>) {
        favoriteAdapter = FavoriteFilmAdapter(favoriteFilms)
        favoriteFilmRecyclerView.adapter = favoriteAdapter
    }

    companion object {
        fun newInstance() : FavoriteFilmListFragment{
            return FavoriteFilmListFragment()
        }
    }
}
