package com.franzandel.moviesubmission.presentation.detailfavouritemovie.activity

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.external.extension.*
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.databinding.ActivityDetailFavouriteMovieBinding
import com.franzandel.moviesubmission.presentation.detailfavouritemovie.vm.DetailFavouriteMovieVM
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFavouriteMovieActivity :
    BaseActivityVM<DetailFavouriteMovieVM, ActivityDetailFavouriteMovieBinding>() {

    private val viewModel: DetailFavouriteMovieVM by viewModels()

    private val favouriteMovieResUI by lazy {
        intent.getParcelableExtra<FavouriteMovieResUI>(BundleConst.EXTRA_FAVOURITE_MOVIE_RES_UI)
    }

    override fun onActivityCreated() {
        setupUI()
        setupListeners()
        setupObservers()
    }

    private fun setupUI() {
        favouriteMovieResUI?.let { popularMovieResUI ->
            binding.apply {
                val backdropUrl =
                    BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.backdropPath
                Glide.with(this@DetailFavouriteMovieActivity)
                    .load(backdropUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivTbDetail)

                val posterUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.posterPath
                Glide.with(this@DetailFavouriteMovieActivity)
                    .load(posterUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)

                tvTitle.text = popularMovieResUI.title
                tvVote.text = popularMovieResUI.voteAverage.mapVoteAverage()
                tvTotalVote.text = popularMovieResUI.voteCount.mapVoteCount()
                tvReleaseDate.text = popularMovieResUI.releaseDate.mapReleaseDate()
                tvLanguage.text = popularMovieResUI.originalLanguage
                tvOverview.text = popularMovieResUI.overview
                ivFavourite.isSelected = popularMovieResUI.isFavourite
            }
        }
    }

    private fun setupListeners() {
        binding.tbDetail.setNavigationOnClickListener {
            finish()
        }

        binding.tbDetail.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share ->
                    showShareMessage(
                        getString(R.string.share_message_title),
                        getString(R.string.share_message_description, favouriteMovieResUI?.title)
                    )
            }
            true
        }

        binding.ivFavourite.setOnClickListener {
            favouriteMovieResUI?.let { favouriteMovieResUI ->
                if (binding.ivFavourite.isSelected)
                    viewModel.deleteFavouriteMovie(favouriteMovieResUI)
                else
                    viewModel.insertFavouriteMovie(favouriteMovieResUI)
            }
        }
    }

    private fun setupObservers() {
        observe(viewModel.insertFavouriteMovie) {
            binding.ivFavourite.isSelected = true
            favouriteMovieResUI?.isFavourite = true
        }

        observe(viewModel.deleteFavouriteMovie) {
            binding.ivFavourite.isSelected = false
            favouriteMovieResUI?.isFavourite = false
        }
    }

    override fun getVM(): DetailFavouriteMovieVM = viewModel

    override fun getViewBinding(): ActivityDetailFavouriteMovieBinding =
        ActivityDetailFavouriteMovieBinding.inflate(layoutInflater)
}