package com.franzandel.moviesubmission.presentation.popularmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.popularmovies.diffcallback.PopularMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.vh.PopularMoviesVH

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesAdapter(
    private val favouriteClicked: (popularMovieResUI: PopularMovieResUI, ivFavourite: ImageView) -> Unit,
    private val detailClicked: (popularMovieResUI: PopularMovieResUI) -> Unit
) : BaseAdapter<PopularMovieResUI, PopularMoviesVH, ItemPopularMoviesBinding>(
    PopularMoviesDiffCallback()
) {

    private lateinit var viewHolder: PopularMoviesVH

    override fun getViewBinding(parent: ViewGroup): ItemPopularMoviesBinding =
        ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun getViewHolder(viewBinding: ItemPopularMoviesBinding): PopularMoviesVH {
        viewHolder = PopularMoviesVH(viewBinding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: PopularMoviesVH, position: Int) {
        holder.bind(currentList[position], favouriteClicked, detailClicked)
    }
}