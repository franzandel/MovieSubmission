package com.franzandel.moviesubmission.data.local

import com.franzandel.moviesubmission.core.data.wrapper.Result

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

interface LoginLocalData {
    suspend fun storeUsername(username: String): Result<Unit>
    suspend fun getUsername(): Result<String>
}