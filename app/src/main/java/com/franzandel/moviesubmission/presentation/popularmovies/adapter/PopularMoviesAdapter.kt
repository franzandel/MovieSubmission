package com.franzandel.moviesubmission.presentation.popularmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.popularmovies.diffcallback.PopularMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.vh.PopularMoviesVH
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesAdapter(
    private val popularMoviesVM: PopularMoviesVM,
    private val navigation: MoviesNavigation
) : BaseAdapter<PopularMovieResUI, PopularMoviesVH, ItemPopularMoviesBinding>(
    PopularMoviesDiffCallback()
) {

    override fun getViewBinding(parent: ViewGroup): ItemPopularMoviesBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_popular_movies,
            parent,
            false
        )

    override fun getViewHolder(viewBinding: ItemPopularMoviesBinding): PopularMoviesVH =
        PopularMoviesVH(viewBinding, popularMoviesVM, navigation)

    override fun onBindViewHolder(holder: PopularMoviesVH, position: Int) {
        holder.bind(currentList[position])
    }
}