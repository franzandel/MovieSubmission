package com.franzandel.moviesubmission.core.mapper

import com.franzandel.moviesubmission.core.data.entity.MoviesErrorResDTO
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.UNKNOWN_ERROR
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
abstract class RetrofitResMapper<SourceType, ResultType>(private val gson: Gson) :
    BaseMapper<SourceType, ResultType>() {

    protected open fun mapErrorMessage(errorBody: ResponseBody): String =
        gson.fromJson<MoviesErrorResDTO>(
            errorBody.charStream().readText(),
            object : TypeToken<MoviesErrorResDTO>() {}.type
        ).statusMessage

    private fun mapException(errorBody: ResponseBody?): Exception = try {
        Exception(mapErrorMessage(errorBody ?: throw Exception(UNKNOWN_ERROR)))
    } catch (e: Exception) {
        e
    }

    protected open fun mapErrorCode(response: Response<SourceType>): Int = response.code()

    protected open fun mapErrorResult(response: Response<SourceType>): Result.Error =
        with(response) {
            Result.Error(error = mapException(errorBody()), code = mapErrorCode(response))
        }

    open fun mapResponse(response: Response<SourceType>): Result<ResultType> = with(response) {
        val responseBody = body()

        if (isSuccessful) {
            if (responseBody != null) Result.Success(map(responseBody))
            else Result.Success(Unit as ResultType)
        } else {
            mapErrorResult(response = response)
        }
    }
}
