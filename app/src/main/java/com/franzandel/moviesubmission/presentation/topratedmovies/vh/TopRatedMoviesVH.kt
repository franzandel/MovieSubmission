package com.franzandel.moviesubmission.presentation.topratedmovies.vh

import androidx.recyclerview.widget.RecyclerView
import com.franzandel.moviesubmission.databinding.ItemTopRatedMoviesBinding

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesVH(private val itemTopRatedMoviesBinding: ItemTopRatedMoviesBinding) :
    RecyclerView.ViewHolder(itemTopRatedMoviesBinding.root) {

    fun bind(gamesResult: String) {
        with(itemTopRatedMoviesBinding) {
            tvTitle.text = "Title"
            tvGenre.text = "Genre here"
            ivFavourite.isSelected = true
        }
    }
}