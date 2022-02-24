package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatch_to_movie_application.viewmodels.FilmListViewModel

private const val TAG_FILMLIST = "FilmListFragment"

class FilmListFragment : Fragment() {

    interface Callbacks {
        fun onDetailsFilmSelected(nameFilmId : Int)
    }

    private var callbacks: Callbacks? = null
    private lateinit var  filmRecyclerView: RecyclerView
    private var filmAdapter : FilmAdapter? = FilmAdapter(emptyList())

    private val filmListViewModel : FilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmListViewModel.filmListLiveData.observe(
            viewLifecycleOwner,
            Observer { films ->
                films.let {
                    updateUI(films)
                }
            }
        )
    }

    private inner class FilmHolder (view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var film: FilmsItem

        private val imageFilmView : ImageView = itemView.findViewById(R.id.film_image)
        private val nameFilmView : TextView = itemView.findViewById(R.id.name_film)
        private val buttonDetailsView : Button = itemView.findViewById(R.id.details_button)

        init {
            buttonDetailsView.setOnClickListener {
            //Вызов фрагмента детального просмотра фильма через Callbacks
                callbacks?.onDetailsFilmSelected(film.nameFilmId)
            }
        }

        @SuppressLint("ResourceType")
        fun bind (film : FilmsItem)
        {
            //this.film = film
            nameFilmView.text = resources.getString(film.nameFilmId)
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


    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(films: List<FilmsItem>) {
        filmAdapter = FilmAdapter(films)
        filmRecyclerView.adapter = filmAdapter
    }
    companion object{
        fun newInstance() : FilmListFragment {
            return FilmListFragment()
        }
    }
}