package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG_FILMLIST = "FilmListFragment"

class FilmListFragment : Fragment() {

    private lateinit var  filmRecyclerView: RecyclerView
    private var adapter : FilmAdapter? = null

    private val filmListViewModel : FilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_FILMLIST, "Total films: ${filmListViewModel.filmList.size}")
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_film_list, container, false)
        filmRecyclerView = view.findViewById(R.id.film_recycler_view) as RecyclerView
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            filmRecyclerView.layoutManager = GridLayoutManager(context, 3)
        }
        else {
            filmRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        updateUI()
        return view
    }

    private inner class FilmHolder (view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var film: FilmsItem

        private val imageFilmView : ImageView = itemView.findViewById(R.id.film_image)
        private val nameFilmView : TextView = itemView.findViewById(R.id.film_name)
        private val buttonDetailsView : Button = itemView.findViewById(R.id.details_button)
        private val isFavoriteView : CheckBox = itemView.findViewById(R.id.is_favorite)

        init {
            isFavoriteView.setOnCheckedChangeListener { _, isChecked ->
                film.isFavorite = isChecked
                if (isChecked) {
                    Toast.makeText(
                        context,
                        "Film ${resources.getString(film.nameFilmId)} is Favorite!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Film ${resources.getString(film.nameFilmId)} is not Favorite!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            buttonDetailsView.setOnClickListener {
                parentFragmentManager.setFragmentResult("result", Bundle().apply {
                    //putInt("name", film.nameFilmId)
                    putInt("description", film.descriptionFilmId)
                    putInt("image", film.imageId)
                })
                parentFragmentManager.beginTransaction()
                    .remove(this@FilmListFragment)
                    .replace(R.id.head_container, DetailsFragment())
                    .addToBackStack("string")
                    .commit()
            }
        }

        @SuppressLint("ResourceType")
        fun bind (film : FilmsItem)
        {
            this.film = film
            nameFilmView.text = resources.getString(film.nameFilmId)
            isFavoriteView.isChecked = film.isFavorite
            isFavoriteView.text = resources.getString(R.string.isFavorite)
            buttonDetailsView.text = resources.getString(R.string.details)
            imageFilmView.setImageResource(film.imageId)
        }


    }

    private inner class FilmAdapter(var films : List<FilmsItem>)
        : RecyclerView.Adapter<FilmHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {

            val view = layoutInflater.inflate(R.layout.film_list_item, parent, false)
            return FilmHolder(view)
        }

        override fun getItemCount(): Int {
            return films.size
        }

        override fun onBindViewHolder(holder: FilmHolder, position: Int) {

            val film = films[position]
            holder.bind(film)
        }
    }

    private fun updateUI() {
        val films = filmListViewModel.filmList
        adapter = FilmAdapter(films)
        filmRecyclerView.adapter = adapter
    }
    companion object{
        fun newInstance() : FilmListFragment {
            return FilmListFragment()
        }
    }
}