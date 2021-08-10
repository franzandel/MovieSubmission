package com.franzandel.moviesubmission.presentation.popularmovies.vh

import androidx.recyclerview.widget.RecyclerView
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesVH(private val itemPopularMoviesBinding: ItemPopularMoviesBinding) :
    RecyclerView.ViewHolder(itemPopularMoviesBinding.root) {

    fun bind(gamesResult: String) {
        with(itemPopularMoviesBinding) {
            tvTitle.text = "Title"
            tvGenre.text = "Genre here"
            ivFavourite.isSelected = true
        }
    }
}