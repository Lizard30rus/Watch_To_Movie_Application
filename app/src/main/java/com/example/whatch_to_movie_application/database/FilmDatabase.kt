package com.example.whatch_to_movie_application.database

import android.content.Context
import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.whatch_to_movie_application.FilmsItem
import com.example.whatch_to_movie_application.R
import com.example.whatch_to_movie_application.ioThread

@Database(entities = [FilmsItem::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao() : FilmDAO

    companion object{

        @Volatile private var INSTANCE : FilmDatabase? = null

        fun getInstance(context: Context) : FilmDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            FilmDatabase::class.java,
            "Sample.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db : SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).filmDao().addFilms(FILM_LIST)
                        }
                    }
                }).build()


        val FILM_LIST = listOf(
            FilmsItem(R.string.name_Film_1,
            R.drawable.gentl_image,
            R.string.description_Film_1),
            FilmsItem(R.string.name_Film_2,
            R.drawable.cards_cash_two_guns,
            R.string.description_Film_2),
            FilmsItem(R.string.name_Film_3,
            R.drawable.revolver,
            R.string.description_Film_3),
            FilmsItem(R.string.name_Film_4,
            R.drawable.big_score,
            R.string.description_Film_4),
            FilmsItem(R.string.name_Film_5,
                R.drawable.attraction,
                R.string.description_Film_5))
        }


    }