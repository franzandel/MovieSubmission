package com.franzandel.moviesubmission.utils

import com.franzandel.moviesubmission.domain.model.FavouriteMovieReq
import com.franzandel.moviesubmission.domain.model.FavouriteMovieRes
import com.franzandel.moviesubmission.presentation.favouritemovies.model.FavouriteMovieResUI
import com.franzandel.moviesubmission.presentation.popularmovies.model.PopularMovieResUI
import com.franzandel.moviesubmission.presentation.topratedmovies.model.TopRatedMovieResUI

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
            id = 1,
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

    fun getTopRatedMovieResUI(): TopRatedMovieResUI =
        TopRatedMovieResUI(
            id = 1,
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

    fun getFavouriteMovieResUI(): FavouriteMovieResUI =
        FavouriteMovieResUI(
            id = 1,
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
            id = 1,
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

    fun getPopularMoviesResUI(): List<PopularMovieResUI> =
        listOf(
            PopularMovieResUI(
                id = 1,
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
            ),
            PopularMovieResUI(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genres = "Action, Comedy, Adventure",
                originalLanguage = "en",
                originalTitle = "Monster Hunter",
                overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                popularity = 2021.063,
                posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                releaseDate = "2020-12-03",
                title = "Monster Hunter",
                video = false,
                voteAverage = 7.1,
                voteCount = 1292,
                isFavourite = true
            )
        )

    fun getTopRatedMoviesResUI(): List<TopRatedMovieResUI> =
        listOf(
            TopRatedMovieResUI(
                id = 1,
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
            ),
            TopRatedMovieResUI(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genres = "Action, Comedy, Adventure",
                originalLanguage = "en",
                originalTitle = "Monster Hunter",
                overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                popularity = 2021.063,
                posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                releaseDate = "2020-12-03",
                title = "Monster Hunter",
                video = false,
                voteAverage = 7.1,
                voteCount = 1292,
                isFavourite = true
            )
        )

    fun getFavouriteMoviesRes(): List<FavouriteMovieRes> =
        listOf(
            FavouriteMovieRes(
                id = 1,
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
            ),
            FavouriteMovieRes(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genres = "Action, Comedy, Adventure",
                originalLanguage = "en",
                originalTitle = "Monster Hunter",
                overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                popularity = 2021.063,
                posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                releaseDate = "2020-12-03",
                title = "Monster Hunter",
                video = false,
                voteAverage = 7.1,
                voteCount = 1292
            )
        )

    fun getFavouriteMoviesResUI(): List<FavouriteMovieResUI> =
        listOf(
            FavouriteMovieResUI(
                id = 1,
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
            ),
            FavouriteMovieResUI(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genres = "Action, Comedy, Adventure",
                originalLanguage = "en",
                originalTitle = "Monster Hunter",
                overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                popularity = 2021.063,
                posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                releaseDate = "2020-12-03",
                title = "Monster Hunter",
                video = false,
                voteAverage = 7.1,
                voteCount = 1292,
                isFavourite = true
            )
        )
}