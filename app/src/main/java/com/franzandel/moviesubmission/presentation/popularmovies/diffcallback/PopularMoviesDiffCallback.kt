package com.franzandel.moviesubmission.presentation.popularmovies.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesDiffCallback : DiffUtil.ItemCallback<PopularMovieResUI>() {

    override fun areItemsTheSame(oldItem: PopularMovieResUI, newItem: PopularMovieResUI): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PopularMovieResUI,
        newItem: PopularMovieResUI
    ): Boolean =
        oldItem == newItem
}