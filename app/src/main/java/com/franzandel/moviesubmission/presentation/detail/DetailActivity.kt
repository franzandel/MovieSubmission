package com.franzandel.moviesubmission.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.franzandel.moviesubmission.R
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

class DetailActivity : AppCompatActivity() {

    private val popularMovieResUI by lazy {
        intent.getParcelableExtra<PopularMovieResUI>(BundleConst.EXTRA_POPULAR_MOVIE_RES_UI)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}