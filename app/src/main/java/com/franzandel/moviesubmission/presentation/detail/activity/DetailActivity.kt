package com.franzandel.moviesubmission.presentation.detail.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.databinding.ActivityDetailBinding
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val popularMovieResUI by lazy {
        intent.getParcelableExtra<PopularMovieResUI>(BundleConst.EXTRA_POPULAR_MOVIE_RES_UI)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPopularMovieResUI()
        setupListeners()
    }

    private fun setupPopularMovieResUI() {
        popularMovieResUI?.let { popularMovieResUI ->
            binding.apply {
                val backdropUrl =
                    BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.backdropPath
                Glide.with(this@DetailActivity)
                    .load(backdropUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivTbDetail)

                val posterUrl = BuildConfig.MOVIE_DB_IMAGE_BASE_URL + popularMovieResUI.posterPath
                Glide.with(this@DetailActivity)
                    .load(posterUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)

                tvTitle.text = popularMovieResUI.title
                tvVote.text = mapVoteAverage(popularMovieResUI.voteAverage)
                tvTotalVote.text = mapVoteCount(popularMovieResUI.voteCount)
                tvReleaseDate.text = mapReleaseDate(popularMovieResUI.releaseDate)
                tvLanguage.text = popularMovieResUI.originalLanguage
                tvOverview.text = popularMovieResUI.overview
            }
        }
    }

    private fun mapVoteAverage(voteAverage: Double): String = "$voteAverage/10"

    private fun mapVoteCount(voteCount: Int): String = "$voteCount votes"

    private fun mapReleaseDate(releaseDate: String): String {
        val initialFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parseReleaseDate = initialFormat.parse(releaseDate)
        val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        parseReleaseDate?.let {
            return newFormat.format(parseReleaseDate)
        }
        return releaseDate
    }

    private fun setupListeners() {
        binding.tbDetail.setNavigationOnClickListener {
            finish()
        }

        binding.tbDetail.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share ->
                    Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}