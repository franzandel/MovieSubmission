package com.franzandel.moviesubmission.presentation.favouritemovies.vh

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.databinding.ItemFavouriteMoviesBinding
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class FavouriteMoviesVH(private val itemFavouriteMoviesBinding: ItemFavouriteMoviesBinding) :
    RecyclerView.ViewHolder(itemFavouriteMoviesBinding.root) {

    fun bind(
        favouriteMovieResUI: FavouriteMovieResUI,
        detailClicked: (favouriteMovieResUI: FavouriteMovieResUI) -> Unit
    ) {
        with(itemFavouriteMoviesBinding) {
            tvTitle.text = favouriteMovieResUI.title
            tvGenre.text = favouriteMovieResUI.genres

            val imageUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + favouriteMovieResUI.posterPath
            Glide.with(root.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivFavouriteMovies)

            root.setOnClickListener {
                detailClicked.invoke(favouriteMovieResUI)
            }
        }
    }
}