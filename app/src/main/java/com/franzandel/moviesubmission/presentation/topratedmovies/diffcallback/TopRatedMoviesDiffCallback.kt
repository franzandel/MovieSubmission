package com.franzandel.moviesubmission.presentation.topratedmovies.diffcallback

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}