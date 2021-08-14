package com.franzandel.moviesubmission.utils

import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

object RoomUtils {

    const val ERROR_INSERT_TO_DB = "Error insert to DB"
    const val ERROR_DELETE_FROM_DB = "Error delete from DB"
    const val NO_DATA_FOUND = "No data found on DB"

    fun getPopularMovieResUI(): PopularMovieResUI =
        PopularMovieResUI(
            id = 399566,
            adult = false,
            backdropPath = "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
            genres = "Action, Comedy, Adventure",
            originalLanguage = "en",
            originalTitle = "Godzilla vs. Kong",
            overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            popularity = 9043.741,
            posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            releaseDate = "2021-03-24",
            title = "Godzilla vs. Kong",
            video = false,
            voteAverage = 7.1,
            voteCount = 155,
            isFavourite = true
        )

    fun getFavouriteMovieReq(): FavouriteMovieReq =
        FavouriteMovieReq(
            id = 399566,
            adult = false,
            backdropPath = "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
            genres = "Action, Comedy, Adventure",
            originalLanguage = "en",
            originalTitle = "Godzilla vs. Kong",
            overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            popularity = 9043.741,
            posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            releaseDate = "2021-03-24",
            title = "Godzilla vs. Kong",
            video = false,
            voteAverage = 7.1,
            voteCount = 155
        )
}