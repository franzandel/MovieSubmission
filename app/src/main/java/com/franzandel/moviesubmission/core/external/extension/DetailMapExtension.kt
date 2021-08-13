package com.franzandel.moviesubmission.core.external.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Franz Andel on 13/08/21.
 * Android Engineer
 */

fun Double.mapVoteAverage(): String = "$this/10"

fun Int.mapVoteCount(): String = "$this votes"

fun String.mapReleaseDate(): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parseReleaseDate = initialFormat.parse(this)
    val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    parseReleaseDate?.let {
        return newFormat.format(parseReleaseDate)
    }
    return this
}