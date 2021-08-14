package com.franzandel.moviesubmission.utils

import com.franzandel.moviesubmission.core.external.extension.convertStringToMap
import com.franzandel.moviesubmission.core.external.extension.toDataClass
import com.franzandel.moviesubmission.core.mapper.RetrofitResMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitGenresResMapper
import com.franzandel.moviesubmission.data.remote.mapper.RetrofitMoviesResMapper
import com.franzandel.moviesubmission.data.remote.model.GenresResDTO
import com.franzandel.moviesubmission.data.remote.model.MoviesResDTO
import com.franzandel.moviesubmission.domain.model.GenreRes
import com.franzandel.moviesubmission.domain.model.MovieGenreRes
import com.franzandel.moviesubmission.domain.model.MovieRes
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

/**
 * Created by Franz Andel on 14/08/21.
 * Android Engineer
 */

object RetrofitUtils {

    const val GET_DATA_FAILED = "Get data from server failed"

    fun getMovieRes(): List<MovieRes> =
        listOf(
            MovieRes(
                id = 1,
                adult = false,
                backdropPath = "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
                genreIds = listOf(1, 2),
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
            MovieRes(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genreIds = listOf(3, 4),
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

    fun getGenreRes(): List<GenreRes> =
        listOf(
            GenreRes(
                id = 1,
                name = "Adventure"
            ),
            GenreRes(
                id = 2,
                name = "Comedy"
            ),
            GenreRes(
                id = 3,
                name = "Horror"
            ),
            GenreRes(
                id = 4,
                name = "Thriller"
            )
        )

    fun getMoviesGenresRes(): List<MovieGenreRes> =
        listOf(
            MovieGenreRes(
                id = 1,
                adult = false,
                backdropPath = "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
                genresRes = listOf(
                    GenreRes(
                        id = 1,
                        name = "Adventure"
                    ),
                    GenreRes(
                        id = 2,
                        name = "Comedy"
                    )
                ),
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
                isFavourite = false
            ),
            MovieGenreRes(
                id = 2,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                genresRes = listOf(
                    GenreRes(
                        id = 3,
                        name = "Horror"
                    ),
                    GenreRes(
                        id = 4,
                        name = "Thriller"
                    )
                ),
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
                isFavourite = false
            )
        )

    fun getMoviesResFromJson(): List<MovieRes> {
        val jsonString = getJsonString("movies_response.json")
        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val moviesResDTO = dataInMap.toDataClass<MoviesResDTO>()
        val gson = Gson()
        val mapper: RetrofitResMapper<MoviesResDTO, List<MovieRes>> = RetrofitMoviesResMapper(gson)

        return mapper.map(moviesResDTO)
    }

    fun getGenresResFromJson(): List<GenreRes> {
        val jsonString = getJsonString("genres_response.json")
        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val genresResDTO = dataInMap.toDataClass<GenresResDTO>()
        val gson = Gson()
        val mapper: RetrofitResMapper<GenresResDTO, List<GenreRes>> = RetrofitGenresResMapper(gson)

        return mapper.map(genresResDTO)
    }

    private fun getJsonString(filePath: String): String {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream(filePath)

        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}