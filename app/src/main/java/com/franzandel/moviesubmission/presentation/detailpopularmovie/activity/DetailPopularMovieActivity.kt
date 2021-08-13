package com.franzandel.moviesubmission.presentation.detailpopularmovie.activity

import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.core.external.extension.*
import com.franzandel.moviesubmission.core.presentation.activity.BaseActivityVM
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.databinding.ActivityDetailPopularMovieBinding
import com.franzandel.moviesubmission.presentation.detailpopularmovie.vm.DetailPopularMovieVM
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailPopularMovieActivity :
    BaseActivityVM<DetailPopularMovieVM, ActivityDetailPopularMovieBinding>() {

    private val viewModel: DetailPopularMovieVM by viewModels()

    private val popularMovieResUI by lazy {
        intent.getParcelableExtra<PopularMovieResUI>(BundleConst.EXTRA_POPULAR_MOVIE_RES_UI)
    }

    override fun onActivityCreated() {
        setupUI()
        setupListeners()
        setupObservers()
    }

    private fun setupUI() {
        popularMovieResUI?.let { popularMovieResUI ->
            binding.apply {
                val backdropUrl =
                    BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.backdropPath
                Glide.with(this@DetailPopularMovieActivity)
                    .load(backdropUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivTbDetail)

                val posterUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.posterPath
                Glide.with(this@DetailPopularMovieActivity)
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
                        getString(R.string.share_message_description, popularMovieResUI?.title)
                    )
            }
            true
        }

        binding.ivFavourite.setOnClickListener {
            popularMovieResUI?.let { popularMovieResUI ->
                if (binding.ivFavourite.isSelected)
                    viewModel.deleteFavouriteMovie(popularMovieResUI)
                else
                    viewModel.insertFavouriteMovie(popularMovieResUI)
            }
        }
    }

    private fun setupObservers() {
        observe(viewModel.insertFavouriteMovie) {
            binding.ivFavourite.isSelected = true
            popularMovieResUI?.isFavourite = true
        }

        observe(viewModel.deleteFavouriteMovie) {
            binding.ivFavourite.isSelected = false
            popularMovieResUI?.isFavourite = false
        }
    }

    override fun getVM(): DetailPopularMovieVM = viewModel

    override fun getViewBinding(): ActivityDetailPopularMovieBinding =
        ActivityDetailPopularMovieBinding.inflate(layoutInflater)
}