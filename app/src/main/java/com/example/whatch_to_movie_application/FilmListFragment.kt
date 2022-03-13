package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatch_to_movie_application.viewmodels.FilmListViewModel


private const val TAG = "TAG"
class FilmListFragment : Fragment() {

    interface Callbacks {
        fun onDetailsFromFilmListFilmSelected(nameFilmId : Int)
    }

    private var callbacks: Callbacks? = null
    private lateinit var  filmRecyclerView: RecyclerView
    private var filmAdapter : FilmAdapter? = FilmAdapter(emptyList())

    private val filmListViewModel : FilmListViewModel by lazy {
        ViewModelProviders.of(this).get(FilmListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "FilmListFragment on Attach")
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val filmlist = listOf(
            FilmsItem(R.string.test_name,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_1,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_2,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_3,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_4,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_5,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_6,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_7,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_8,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_9,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_10,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_11,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_12,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_13,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_14,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_15,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_16,R.drawable.android, R.string.test_description),
            FilmsItem(R.string.test_name_17,R.drawable.android, R.string.test_description),
        )
        filmListViewModel.addFilms(filmlist)*/
        Log.d(TAG, "FilmListFragment on Create")
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
        Log.d(TAG, "FilmListFragment on CreateView")

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
        Log.d(TAG, "FilmListFragment on ViewCreated")
    }

    private inner class FilmHolder (view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var film: FilmsItem

        private val imageFilmView : ImageView = itemView.findViewById(R.id.film_image)
        private val nameFilmView : TextView = itemView.findViewById(R.id.name_film)
        private val buttonDetailsView : Button = itemView.findViewById(R.id.details_button)

        @SuppressLint("ResourceType")
        fun bind (film : FilmsItem)
        {
            this.film = film
            nameFilmView.text  =  resources.getString(film.nameFilmId)
            buttonDetailsView.text = resources.getString(R.string.details)
            imageFilmView.setImageResource(film.imageId)
        }
        init {
            buttonDetailsView.setOnClickListener {
                callbacks?.onDetailsFromFilmListFilmSelected(film.nameFilmId)
            }
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
        Log.d(TAG, "FilmListFragment on Detach")
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

   /* override fun onStop() {
        super.onStop()
        Log.d(TAG, "FilmListFragment on Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "FilmListFragment on Destroy")
    }*/
}