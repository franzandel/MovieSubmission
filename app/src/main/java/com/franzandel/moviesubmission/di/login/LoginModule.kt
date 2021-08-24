package com.franzandel.moviesubmission.di.login

import com.franzandel.moviesubmission.data.remote.network.LoginNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Franz Andel on 24/08/21.
 * Android Engineer
 */

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    @ViewModelScoped
    fun provideLoginNetworkService(@Named("Login_API") retrofit: Retrofit): LoginNetworkService =
        retrofit.create(LoginNetworkService::class.java)
}