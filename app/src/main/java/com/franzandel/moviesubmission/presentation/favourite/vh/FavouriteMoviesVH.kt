package com.franzandel.moviesubmission.presentation.favourite.vh

import androidx.recyclerview.widget.RecyclerView
import com.franzandel.moviesubmission.databinding.ItemFavouriteMoviesBinding

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class FavouriteMoviesVH(private val itemFavouriteMoviesBinding: ItemFavouriteMoviesBinding) :
    RecyclerView.ViewHolder(itemFavouriteMoviesBinding.root) {

    fun bind(gamesResult: String) {
        with(itemFavouriteMoviesBinding) {
            tvTitle.text = "Title"
            tvGenre.text = "Genre here"
        }
    }
}