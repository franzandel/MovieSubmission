package com.franzandel.moviesubmission.presentation.favouritemovies.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class FavouriteMoviesDiffCallback : DiffUtil.ItemCallback<FavouriteMovieResUI>() {

    override fun areItemsTheSame(
        oldItem: FavouriteMovieResUI,
        newItem: FavouriteMovieResUI
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: FavouriteMovieResUI,
        newItem: FavouriteMovieResUI
    ): Boolean =
        oldItem == newItem
}