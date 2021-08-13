package com.franzandel.moviesubmission.presentation.detailtopratedmovie.activity

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.external.extension.*
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.databinding.ActivityDetailTopRatedMovieBinding
import com.franzandel.moviesubmission.presentation.detailtopratedmovie.vm.DetailTopRatedMovieVM
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailTopRatedMovieActivity :
    BaseActivityVM<DetailTopRatedMovieVM, ActivityDetailTopRatedMovieBinding>() {

    private val viewModel: DetailTopRatedMovieVM by viewModels()

    private val topRatedMovieResUI by lazy {
        intent.getParcelableExtra<TopRatedMovieResUI>(BundleConst.EXTRA_TOP_RATED_MOVIE_RES_UI)
    }

    override fun onActivityCreated() {
        setupUI()
        setupListeners()
        setupObservers()
    }

    private fun setupUI() {
        topRatedMovieResUI?.let { topRatedMovieResUI ->
            binding.apply {
                val backdropUrl =
                    BuildConfig.MOVIE_DB_IMAGE_BASE_URL + topRatedMovieResUI.backdropPath
                Glide.with(this@DetailTopRatedMovieActivity)
                    .load(backdropUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivTbDetail)

                val posterUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + topRatedMovieResUI.posterPath
                Glide.with(this@DetailTopRatedMovieActivity)
                    .load(posterUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)

                tvTitle.text = topRatedMovieResUI.title
                tvVote.text = topRatedMovieResUI.voteAverage.mapVoteAverage()
                tvTotalVote.text = topRatedMovieResUI.voteCount.mapVoteCount()
                tvReleaseDate.text = topRatedMovieResUI.releaseDate.mapReleaseDate()
                tvLanguage.text = topRatedMovieResUI.originalLanguage
                tvOverview.text = topRatedMovieResUI.overview
                ivFavourite.isSelected = topRatedMovieResUI.isFavourite
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
                        getString(R.string.share_message_description, topRatedMovieResUI?.title)
                    )
            }
            true
        }

        binding.ivFavourite.setOnClickListener {
            topRatedMovieResUI?.let { topRatedMovieResUI ->
                if (binding.ivFavourite.isSelected)
                    viewModel.deleteFavouriteMovie(topRatedMovieResUI)
                else
                    viewModel.insertFavouriteMovie(topRatedMovieResUI)
            }
        }
    }

    private fun setupObservers() {
        observe(viewModel.insertFavouriteMovie) {
            binding.ivFavourite.isSelected = true
            topRatedMovieResUI?.isFavourite = true
        }

        observe(viewModel.deleteFavouriteMovie) {
            binding.ivFavourite.isSelected = false
            topRatedMovieResUI?.isFavourite = false
        }
    }

    override fun getVM(): DetailTopRatedMovieVM = viewModel

    override fun getViewBinding(): ActivityDetailTopRatedMovieBinding =
        ActivityDetailTopRatedMovieBinding.inflate(layoutInflater)
}