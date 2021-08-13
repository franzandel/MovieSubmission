package com.franzandel.moviesubmission.presentation.topratedmovies.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesDiffCallback : DiffUtil.ItemCallback<TopRatedMovieResUI>() {

    override fun areItemsTheSame(
        oldItem: TopRatedMovieResUI,
        newItem: TopRatedMovieResUI
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TopRatedMovieResUI,
        newItem: TopRatedMovieResUI
    ): Boolean =
        oldItem == newItem
}