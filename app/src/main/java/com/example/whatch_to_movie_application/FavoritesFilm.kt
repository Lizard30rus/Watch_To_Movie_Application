package com.example.whatch_to_movie_application
/*заккоментированно 14.02.2022, начал работу с фрагментами
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FavoritesFilm : AppCompatActivity() {

    private var filmList = ArrayList<FilmsItem>()


    private val recyclerViewF by lazy { findViewById<RecyclerView>(R.id.favorite_film_recycler) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_film)

        savedInstanceState?.let {
            filmList = it.getSerializable(STATE_COUNTER) as ArrayList<FilmsItem>
        }

        val result = Intent().apply {

            putExtra(RESULT_FAVORITE, filmList)
        }
        setResult(RESULT_OK, result)

        init()

//при нажатии приложение вылетает с IndexOutOfBoundsException, пустой список(?)
        findViewById<Button>(R.id.remove_button).setOnClickListener {
            filmList.removeAt(0)
            recyclerViewF.adapter?.notifyItemRemoved(0)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_COUNTER, filmList)
    }

    private fun init() {

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            val filmList = intent.getSerializableExtra(FAVORITES_FILM) as ArrayList<FilmsItem>
            //val filmList  = intent.getParcelableArrayListExtra<FilmsItem>(FAVORITES_FILM) as ArrayList<FilmsItem>

            // recyclerViewF.layoutManager = LinearLayoutManager(this)
            recyclerViewF.adapter = FavoriteFilmsAdapter(filmList, object : FavoriteFilmsAdapter.FavoriteClickListener{
//При повороте экрана в описании фильма, вызванного из активити избранного возвращает ошибку NullPointerException,
//Скорее всего нужно вызывать описание с передачей списка и возвращать его соответсвтенно в активити избранного
                override fun onDetailsClick(filmItem: FilmsItem) {
                    intent = Intent(this@FavoritesFilm, FilmDescriptionAct::class.java).apply {
                        putExtra(DESCRIPTION_FILM,filmItem.descriptionFilmId)
                        putExtra(IMAGE_FILM_ID, filmItem.imageId)
                    }
                    startActivity(intent)
                }
            })
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val filmList = intent.getSerializableExtra(FAVORITES_FILM) as ArrayList<FilmsItem>
            //val filmList  = intent.getParcelableArrayListExtra<FilmsItem>(FAVORITES_FILM) as ArrayList<FilmsItem>

            // recyclerViewF.layoutManager = LinearLayoutManager(this)
            recyclerViewF.adapter = FavoriteFilmsAdapter(filmList, object : FavoriteFilmsAdapter.FavoriteClickListener{
                override fun onDetailsClick(filmItem: FilmsItem) {
                    intent = Intent(this@FavoritesFilm, FilmDescriptionAct::class.java).apply {
                        putExtra(DESCRIPTION_FILM,filmItem.descriptionFilmId)
                        putExtra(IMAGE_FILM_ID, filmItem.imageId)
                    }
                    startActivity(intent)
                }
            })
        }

    }


    companion object {
        private const val STATE_COUNTER = "favoriteList"
        const val RESULT_FAVORITE = "favorite"
        const val IMAGE_FILM_ID = "image film id"
        const val DESCRIPTION_FILM = "description film"
        const val REQ_CODE_1 = 234
        const val REQ_CODE_2 = 123
    }
  */
