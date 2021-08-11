package com.franzandel.moviesubmission.presentation.popularmovies.vh

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.databinding.ItemPopularMoviesBinding
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class PopularMoviesVH(private val itemPopularMoviesBinding: ItemPopularMoviesBinding) :
    RecyclerView.ViewHolder(itemPopularMoviesBinding.root) {

    fun bind(popularMovieResUI: PopularMovieResUI) {
        with(itemPopularMoviesBinding) {
            tvTitle.text = popularMovieResUI.title
            tvGenre.text = popularMovieResUI.originalTitle
            ivFavourite.isSelected = true

            val imageUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.posterPath
            Glide.with(root.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivPopularMovies)
        }
    }
}