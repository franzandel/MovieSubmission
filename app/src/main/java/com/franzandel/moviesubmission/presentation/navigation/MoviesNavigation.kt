package com.franzandel.moviesubmission.presentation.navigation

import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 12/08/21.
 * Android Engineer
 */

interface MoviesNavigation {
    fun goToDetailPopularMovie(popularMovieResUI: PopularMovieResUI)
}