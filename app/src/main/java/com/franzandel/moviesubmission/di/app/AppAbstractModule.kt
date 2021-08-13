package com.franzandel.moviesubmission.di.app

import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThread
import com.franzandel.moviesubmission.core.external.coroutine.CoroutineThreadImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Franz Andel on 11/08/21.
 * Android Engineer
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AppAbstractModule {

    @Binds
    @Singleton
    abstract fun provideCoroutineThread(coroutineThreadImpl: CoroutineThreadImpl): CoroutineThread
}