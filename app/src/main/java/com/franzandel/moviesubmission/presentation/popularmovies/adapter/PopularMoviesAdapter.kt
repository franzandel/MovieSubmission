package com.franzandel.moviesubmission.presentation.popularmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.popularmovies.diffcallback.PopularMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.popularmovies.vh.PopularMoviesVH

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesAdapter :
    BaseAdapter<String, PopularMoviesVH, ItemPopularMoviesBinding>(PopularMoviesDiffCallback()) {

    override fun getViewBinding(parent: ViewGroup): ItemPopularMoviesBinding =
        ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun getViewHolder(viewBinding: ItemPopularMoviesBinding): PopularMoviesVH =
        PopularMoviesVH(viewBinding)

    override fun onBindViewHolder(holder: PopularMoviesVH, position: Int) {
        holder.bind(currentList[position])
    }
}