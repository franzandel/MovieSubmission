package com.franzandel.moviesubmission.presentation.topratedmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemTopRatedMoviesBinding
import com.franzandel.moviesubmission.presentation.topratedmovies.diffcallback.TopRatedMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.vh.TopRatedMoviesVH

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesAdapter(
    private val favouriteClicked: (topRatedMovieResUI: TopRatedMovieResUI, ivFavourite: ImageView) -> Unit,
    private val detailClicked: (topRatedMovieResUI: TopRatedMovieResUI) -> Unit
) : BaseAdapter<TopRatedMovieResUI, TopRatedMoviesVH, ItemTopRatedMoviesBinding>(
    TopRatedMoviesDiffCallback()
) {

    override fun getViewBinding(parent: ViewGroup): ItemTopRatedMoviesBinding =
        ItemTopRatedMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun getViewHolder(viewBinding: ItemTopRatedMoviesBinding): TopRatedMoviesVH =
        TopRatedMoviesVH(viewBinding)

    override fun onBindViewHolder(holder: TopRatedMoviesVH, position: Int) {
        holder.bind(currentList[position], favouriteClicked, detailClicked)
    }
}