package com.example.whatch_to_movie_application


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), FilmListFragment.Callbacks, FavoriteFilmListFragment.Callbacks{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigaionView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.head_container)



        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.film_list_fragment, R.id.favorite_film_list_fragment, R.id.invite_fragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigaionView.setupWithNavController(navController)



    }

    override fun onDetailsFilmSelected(nameFilmId: Int) {
        Log.d(TAG, "MainActivity.onDetailsFilmSelected: ${resources.getString(nameFilmId)}")

        val fragment = DetailsFragment.newInstance(nameFilmId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.head_container, fragment)
            .addToBackStack("string")
            .commit()
    }

    /*override fun onBackPressed() {
        val dialofFragment = ExitDialog()
        val manger = supportFragmentManager
        dialofFragment.show(manger, "ExitDialog")
    }*/

}