package com.example.whatch_to_movie_application


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val IMAGE_FILM_ID = "image film id"
const val DESCRIPTION_FILM = "description film"
const val FAVORITES_FILM = "favorites film"
const val SAVE_STATE = "save state"


class MainActivity : AppCompatActivity(){

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.film_recycler) }
    private var favoriteFilmList = ArrayList<FilmsItem>()
    private var filmList = ArrayList<FilmsItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            favoriteFilmList = it.getSerializable(SAVE_STATE) as ArrayList<FilmsItem>
        }

        findViewById<Button>(R.id.invite_button).setOnClickListener {
            val intent = Intent(this, InviteActivity::class.java)
            startActivityForResult(intent, REQ_CODE_1)
        }

        findViewById<Button>(R.id.favorites_button).setOnClickListener {

            val intent = Intent (this, FavoritesFilm ::class.java)
            intent.putExtra(FAVORITES_FILM, favoriteFilmList)
            startActivity(intent)
            //startActivityForResult(intent, REQ_CODE_2)
        }

        init()

       // onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_STATE, favoriteFilmList)
    }

    private fun init() {
        filmList.add(FilmsItem(resources.getString(R.string.name_Film_1),
            R.drawable.gentl_image,
            resources.getString(R.string.description_Film_1)))
        filmList.add(FilmsItem(resources.getString(R.string.name_Film_2),
            R.drawable.cards_cash_two_guns,
            resources.getString(R.string.description_Film_2)))
        filmList.add( FilmsItem(resources.getString(R.string.name_Film_3),
            R.drawable.revolver,
            resources.getString(R.string.description_Film_3)))
        filmList.add(FilmsItem(resources.getString(R.string.name_Film_4),
            R.drawable.big_score,
            resources.getString(R.string.description_Film_4)))
        filmList.add( FilmsItem(resources.getString(R.string.name_Film_5),
            R.drawable.attraction,
            resources.getString(R.string.description_Film_5)))

        //recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilmsItemAdapter(filmList, object : FilmsItemAdapter.ItemsClickListener {
            override fun onDetailsClick(filmItem: FilmsItem) {
                intent = Intent(this@MainActivity, FilmDescriptionAct::class.java).apply {
                    putExtra(DESCRIPTION_FILM,filmItem.descriptionFilmId)
                    putExtra(IMAGE_FILM_ID, filmItem.imageId)
                }
                startActivity(intent)
            }
            override fun onFavoriteLongClick(filmItem: FilmsItem) {
                if (favoriteFilmList.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity, "film ${filmItem.nameFilm} added!",
                        Toast.LENGTH_SHORT
                    ).show()
                    favoriteFilmList.add(filmItem)
                    }
                else {
                    for (i in 0 until favoriteFilmList.size) {
                        if (favoriteFilmList[i].nameFilm == filmItem.nameFilm) {
                            Toast.makeText(
                                this@MainActivity,
                                "film ${filmItem.nameFilm} already in favorites!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "film ${filmItem.nameFilm} added!",
                                Toast.LENGTH_SHORT
                            ).show()
                            favoriteFilmList.add(filmItem)
                            break
                        }
                    }
                }
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_1)
        {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "the invitation is sent!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong! :(", Toast.LENGTH_SHORT).show()
            }
        }
        else if (requestCode == REQ_CODE_2) {
            if (resultCode == RESULT_OK) {
                favoriteFilmList = data?.getSerializableExtra(FavoritesFilm.RESULT_FAVORITE) as ArrayList<FilmsItem>
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        val dialofFragment = ExitDialog()
        val manger = supportFragmentManager
        dialofFragment.show(manger, "ExitDialog")
    }






   companion object {
        const val IMAGE_FILM_ID = "image film id"
        const val DESCRIPTION_FILM = "description film"
        const val REQ_CODE_1 = 234
        const val REQ_CODE_2 = 123
    }



}


