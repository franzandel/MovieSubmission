package com.franzandel.moviesubmission.core.external.coroutine

import kotlin.coroutines.CoroutineContext

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

interface CoroutineThread {
    fun main(): CoroutineContext
    fun background(): CoroutineContext
    fun default(): CoroutineContext
}