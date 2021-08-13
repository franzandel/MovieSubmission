package com.franzandel.moviesubmission.presentation.topratedmovies.vh

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.databinding.ItemTopRatedMoviesBinding
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class TopRatedMoviesVH(private val itemTopRatedMoviesBinding: ItemTopRatedMoviesBinding) :
    RecyclerView.ViewHolder(itemTopRatedMoviesBinding.root) {

    fun bind(
        topRatedMovieResUI: TopRatedMovieResUI,
        favouriteClicked: (topRatedMovieResUI: TopRatedMovieResUI, ivFavourite: ImageView) -> Unit,
        detailClicked: (topRatedMovieResUI: TopRatedMovieResUI) -> Unit
    ) {
        with(itemTopRatedMoviesBinding) {
            tvTitle.text = topRatedMovieResUI.title
            tvGenre.text = topRatedMovieResUI.genres
            ivFavourite.isSelected = topRatedMovieResUI.isFavourite

            val imageUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + topRatedMovieResUI.posterPath
            Glide.with(root.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivTopRatedMovies)

            ivFavourite.setOnClickListener {
                favouriteClicked.invoke(topRatedMovieResUI, ivFavourite)
            }

            root.setOnClickListener {
                detailClicked.invoke(topRatedMovieResUI)
            }
        }
    }
}