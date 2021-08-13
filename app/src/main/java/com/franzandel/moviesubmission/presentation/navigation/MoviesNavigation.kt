package com.franzandel.moviesubmission.presentation.navigation

import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

interface MoviesNavigation {
    fun goToDetailPopularMovie(popularMovieResUI: PopularMovieResUI)
    fun goToDetailTopRatedMovie(topRatedMovieResUI: TopRatedMovieResUI)
    fun goToDetailFavouriteMovie(favouriteMovieResUI: FavouriteMovieResUI)
}