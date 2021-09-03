package com.franzandel.moviesubmission.presentation.popularmovies.vh

import androidx.recyclerview.widget.RecyclerView
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.navigation.MoviesNavigation
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.vm.PopularMoviesVM

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesVH(
    private val itemPopularMoviesBinding: ItemPopularMoviesBinding,
    private val popularMoviesVM: PopularMoviesVM,
    private val navigation: MoviesNavigation
) : RecyclerView.ViewHolder(itemPopularMoviesBinding.root) {

    fun bind(popularMovieResUI: PopularMovieResUI) {
        with(itemPopularMoviesBinding) {
            itemPosition = adapterPosition
            imageUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.posterPath
            this.popularMovieResUI = popularMovieResUI
            this.navigation = this@PopularMoviesVH.navigation
            viewModel = popularMoviesVM
        }
    }
}