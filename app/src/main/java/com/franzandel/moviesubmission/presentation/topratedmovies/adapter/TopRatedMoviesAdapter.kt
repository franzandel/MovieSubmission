package com.franzandel.moviesubmission.presentation.topratedmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemTopRatedMoviesBinding
import com.franzandel.moviesubmission.presentation.topratedmovies.diffcallback.TopRatedMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.topratedmovies.vh.TopRatedMoviesVH

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesAdapter :
    BaseAdapter<String, TopRatedMoviesVH, ItemTopRatedMoviesBinding>(TopRatedMoviesDiffCallback()) {

    override fun getViewBinding(parent: ViewGroup): ItemTopRatedMoviesBinding =
        ItemTopRatedMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun getViewHolder(viewBinding: ItemTopRatedMoviesBinding): TopRatedMoviesVH =
        TopRatedMoviesVH(viewBinding)

    override fun onBindViewHolder(holder: TopRatedMoviesVH, position: Int) {
        holder.bind(currentList[position])
    }
}