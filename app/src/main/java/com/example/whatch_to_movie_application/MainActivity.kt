package com.example.whatch_to_movie_application


import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), FilmListFragment.Callbacks, FavoriteFilmListFragment.Callbacks{

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        navController = findNavController(R.id.head_container)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.film_list_fragment, R.id.favorite_film_list_fragment, R.id.invite_fragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)



    }

    override fun onDetailsFromFavoriteFilmSelected(nameFilmId: Int) {
        Log.d(TAG, "MainActivity.onDetailsFilmSelected: ${resources.getString(nameFilmId)}")
        val bundle = Bundle().apply {
            putInt(DetailsFragment.DESCRIPTION_FILM, nameFilmId)
        }
        navController.navigate(R.id.action_favorite_film_list_fragment_to_detailsFragment, bundle)

    }

    override fun onDetailsFromFilmListFilmSelected(nameFilmId: Int) {
        val bundle = Bundle().apply {
            putInt(DetailsFragment.DESCRIPTION_FILM, nameFilmId)
        }
       navController.navigate(R.id.action_film_list_fragment_to_detailsFragment, bundle)
    }

}