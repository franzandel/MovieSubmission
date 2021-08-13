package com.franzandel.moviesubmission.presentation.favouritemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.moviesubmission.core.presentation.adapter.BaseAdapter
import com.franzandel.moviesubmission.databinding.ItemFavouriteMoviesBinding
import com.franzandel.moviesubmission.presentation.favouritemovies.diffcallback.FavouriteMoviesDiffCallback
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.presentation.favouritemovies.vh.FavouriteMoviesVH

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

class FavouriteMoviesAdapter(
    private val detailClicked: (favouriteMovieResUI: FavouriteMovieResUI) -> Unit
) : BaseAdapter<FavouriteMovieResUI, FavouriteMoviesVH, ItemFavouriteMoviesBinding>(
    FavouriteMoviesDiffCallback()
) {

    override fun getViewBinding(parent: ViewGroup): ItemFavouriteMoviesBinding =
        ItemFavouriteMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun getViewHolder(viewBinding: ItemFavouriteMoviesBinding): FavouriteMoviesVH =
        FavouriteMoviesVH(viewBinding)

    override fun onBindViewHolder(holder: FavouriteMoviesVH, position: Int) {
        holder.bind(currentList[position], detailClicked)
    }
}