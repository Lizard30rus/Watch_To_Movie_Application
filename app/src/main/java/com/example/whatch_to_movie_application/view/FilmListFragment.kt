package com.example.whatch_to_movie_application.view

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
import com.bumptech.glide.Glide
import com.example.whatch_to_movie_application.FilmIntentApplication
import com.example.whatch_to_movie_application.data.l.entity.FilmsItem
import com.example.whatch_to_movie_application.R
import com.example.whatch_to_movie_application.data.l.entity.FilmsItemModel
import com.example.whatch_to_movie_application.viewmodels.FilmListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "TAG"
class FilmListFragment : Fragment() {

    interface Callbacks {
        fun onDetailsFromFilmListFilmSelected(id : Int)
    }

    //Заполнение фрагмента данными, полученными из веба
    val items = mutableListOf<FilmsItem>()

    private var callbacks: Callbacks? = null

    private lateinit var  filmRecyclerView: RecyclerView
    private var filmAdapter = FilmAdapter(items)
    private val linlayoutmanager =  LinearLayoutManager(context)
    private val gridlayoutmanager =  GridLayoutManager(context, 3)

    //переменная для проверки, заполнени ли список items данными из веба
    private var isLoading = false

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
        Log.d(TAG, "FilmListFragment on ViewCreated")
        val view = inflater.inflate(R.layout.fragment_film_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmRecyclerView = view.findViewById(R.id.film_recycler_view) as RecyclerView
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            filmRecyclerView.layoutManager = gridlayoutmanager
        }
        else {
            filmRecyclerView.layoutManager = linlayoutmanager
        }

        //Проверка, есть ли фильмы в базе данных
        if (isLoading) {
            filmListViewModel.filmListLiveData.observe(
                viewLifecycleOwner,
                Observer { films ->
                    films.let {
                        updateUI(films)
                    }
                })
        } else {
            filmRecyclerView.adapter = filmAdapter
            FilmIntentApplication.instance.filmsApi.getFilmsFromApi()
                .enqueue(object : Callback<List<FilmsItemModel>?> {
                    override fun onResponse(
                        call: Call<List<FilmsItemModel>?>,
                        response: Response<List<FilmsItemModel>?>
                    ) {
                        items.clear()
                        if (response.isSuccessful) {
                            Log.d("TAG", "Success!")
                            response.body()?.forEach{
                                items.add(
                                    FilmsItem(it.imageFilm,
                                        it.id,
                                        it.nameFilm,
                                        it.descriptionFilm)
                                )
                            }
                            filmListViewModel.addFilms(items)
                            isLoading = true
                        }
                        filmAdapter?.notifyDataSetChanged()
                    }
                    override fun onFailure(call: Call<List<FilmsItemModel>?>, t: Throwable) {
                        Log.d("TAG", "Failure! ")
                    }
                })
        }

        //пагинация (НЕ РЕАЛИЗОВАНО)
        filmRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
    }

    private inner class FilmHolder (view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var film: FilmsItem

        private val imageFilmView : ImageView = itemView.findViewById(R.id.film_image)
        private val nameFilmView : TextView = itemView.findViewById(R.id.name_film)
        private val buttonDetailsView : Button = itemView.findViewById(R.id.details_button)

        @SuppressLint("ResourceType")
        fun bind (film : FilmsItem)
        {
            nameFilmView.text  = film.nameFilm
            buttonDetailsView.text = resources.getString(R.string.details)

            Glide.with(imageFilmView.context)
                .load(film.imageFilm)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.android)
                .centerCrop()
                .into(imageFilmView)
        }

        init {
            buttonDetailsView.setOnClickListener {
                callbacks?.onDetailsFromFilmListFilmSelected(film.id)
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
}