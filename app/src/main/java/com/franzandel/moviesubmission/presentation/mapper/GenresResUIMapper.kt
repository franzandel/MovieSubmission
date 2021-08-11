package com.franzandel.moviesubmission.presentation.mapper

import com.franzandel.moviesubmission.core.mapper.BaseMapper
import com.franzandel.moviesubmission.domain.model.GenreRes

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

class GenresResUIMapper : BaseMapper<List<GenreRes>, String>() {

    override fun map(dataModel: List<GenreRes>): String {
        val stringBuilder = StringBuilder()
        val comma = ", "

        dataModel.forEachIndexed { index, genreRes ->
            stringBuilder.append(genreRes.name)
            if (index != dataModel.size - 1) stringBuilder.append(comma)
        }

        return stringBuilder.toString()
    }
}