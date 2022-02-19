package com.example.whatch_to_movie_application

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

/*заккоментированно 14.02.2022, начал работу с фрагментами
class FilmsItemAdapter(private val filmList: List<FilmsItem>,
                       private val listener: ItemsClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FilmsItemViewHolder(filmsItem : View) : RecyclerView.ViewHolder(filmsItem) {

        private val nameFilm : TextView = filmsItem.findViewById(R.id.nameFilm)
        private val imageFilm : ImageView = filmsItem.findViewById(R.id.imageFilm)
        private val buttonDetails : Button = filmsItem.findViewById(R.id.buttonDetails)

        @SuppressLint("ResourceAsColor")
        fun bind (filmsItem: FilmsItem, listener: ItemsClickListener) {
            nameFilm.text = filmsItem.nameFilm
            imageFilm.setImageResource(filmsItem.imageId)

            buttonDetails.setOnClickListener {
                nameFilm.setTextColor(R.color.click)
                listener.onDetailsClick(filmsItem)
            }

            imageFilm.setOnLongClickListener {
                listener.onFavoriteLongClick(filmsItem)
                return@setOnLongClickListener true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return FilmsItemViewHolder(view.inflate(R.layout.item_films, parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmsItemViewHolder -> {
                holder.bind(filmList[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = filmList.size

    interface ItemsClickListener {
        fun onDetailsClick (filmItem : FilmsItem)
        fun onFavoriteLongClick (filmItem : FilmsItem)
    }


 */

