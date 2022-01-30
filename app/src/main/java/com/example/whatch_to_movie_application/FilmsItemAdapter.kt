package com.example.whatch_to_movie_application

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

private const val HEADER_VIEW_TYPE = 0
private const val ITEM_VIEW_TYPE = 1

class FilmsItemAdapter(private val items : List<FilmsItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            HEADER_VIEW_TYPE -> HeaderViewHolder(
                inflater.inflate(R.layout.header_film, parent, false)
            )
            else -> FilmsItemViewHolder ( inflater.inflate(R.layout.item_films, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmsItemViewHolder -> {
                holder.bind(items[position - 1])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_VIEW_TYPE else ITEM_VIEW_TYPE
    }

}