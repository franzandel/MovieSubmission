package com.franzandel.moviesubmission.presentation.navigation

import android.content.Context
import com.franzandel.moviesubmission.core.external.extension.goTo
import com.franzandel.moviesubmission.data.consts.BundleConst
import com.franzandel.moviesubmission.presentation.detailfavouritemovie.activity.DetailFavouriteMovieActivity
import com.franzandel.moviesubmission.presentation.detailpopularmovie.activity.DetailPopularMovieActivity
import com.franzandel.moviesubmission.presentation.detailtopratedmovie.activity.DetailTopRatedMovieActivity
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MoviesNavigationImpl @Inject constructor(@ActivityContext private val context: Context) :
    MoviesNavigation {

    override fun goToDetailPopularMovie(popularMovieResUI: PopularMovieResUI) {
        context.goTo(DetailPopularMovieActivity::class.java) {
            putExtra(BundleConst.EXTRA_POPULAR_MOVIE_RES_UI, popularMovieResUI)
        }
    }

    override fun goToDetailTopRatedMovie(topRatedMovieResUI: TopRatedMovieResUI) {
        context.goTo(DetailTopRatedMovieActivity::class.java) {
            putExtra(BundleConst.EXTRA_TOP_RATED_MOVIE_RES_UI, topRatedMovieResUI)
        }
    }

    override fun goToDetailFavouriteMovie(favouriteMovieResUI: FavouriteMovieResUI) {
        context.goTo(DetailFavouriteMovieActivity::class.java) {
            putExtra(BundleConst.EXTRA_FAVOURITE_MOVIE_RES_UI, favouriteMovieResUI)
        }
    }
}