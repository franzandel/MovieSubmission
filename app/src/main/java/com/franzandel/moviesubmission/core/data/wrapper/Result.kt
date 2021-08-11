package com.franzandel.moviesubmission.core.data.wrapper

sealed class Result<out T> {
    data class Success<out S>(val data: S) : Result<S>()
    data class Error(val error: Exception, val code: Int = -1) :
        Result<Nothing>()
}

const val UNKNOWN_ERROR = "unknown error"

val <T> Result<T>.data: T
    get() = when (this) {
        is Result.Success -> this.data
        is Result.Error -> throw error
    }

val <T> Result<T>.errorMessage: String
    get() = (this as Result.Error).error.localizedMessage
        ?: throw IllegalStateException(UNKNOWN_ERROR)

val <T> Result<T>.errorCode: Int
    get() = (this as Result.Error).code

val <T> Result<T>.error: Exception
    get() = (this as Result.Error).error
