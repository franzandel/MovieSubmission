package com.franzandel.moviesubmission.presentation.navigation

import android.content.Context
import com.franzandel.moviesubmission.core.external.extension.goTo
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.presentation.detailpopularmovie.activity.DetailPopularMovieActivity
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MoviesNavigationImpl @Inject constructor(@ActivityContext private val context: Context) :
    MoviesNavigation {

    override fun goToDetailPopularMovie(popularMovieResUI: PopularMovieResUI) {
        context.goTo(DetailPopularMovieActivity::class.java) {
            putExtra(BundleConst.EXTRA_POPULAR_MOVIE_RES_UI, popularMovieResUI)
        }
    }
}