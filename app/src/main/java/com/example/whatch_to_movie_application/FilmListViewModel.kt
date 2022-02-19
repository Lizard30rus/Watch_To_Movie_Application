package com.example.whatch_to_movie_application


import androidx.lifecycle.ViewModel


class FilmListViewModel : ViewModel() {

    val filmList = mutableListOf<FilmsItem>()

    init {
        filmList.add(FilmsItem(R.string.name_Film_1,
            R.drawable.gentl_image,
            R.string.description_Film_1))
        filmList.add(FilmsItem(R.string.name_Film_2,
            R.drawable.cards_cash_two_guns,
            R.string.description_Film_2))
        filmList.add( FilmsItem(R.string.name_Film_3,
            R.drawable.revolver,
            R.string.description_Film_3))
        filmList.add(FilmsItem(R.string.name_Film_4,
            R.drawable.big_score,
            R.string.description_Film_4))
        filmList.add( FilmsItem(R.string.name_Film_5,
            R.drawable.attraction,
            R.string.description_Film_5))
    }
}