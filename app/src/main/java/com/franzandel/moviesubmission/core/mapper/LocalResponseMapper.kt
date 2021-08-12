package com.franzandel.moviesubmission.core.mapper

import com.franzandel.moviesubmission.core.data.wrapper.Result

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

fun <ResultType> mapLocalResponse(
    dbResponse: Number,
    successType: ResultType,
    errorMessage: String
): Result<ResultType> =
    if (dbResponse.toLong() >= 0)
        Result.Success(successType)
    else
        Result.Error(Exception(errorMessage))