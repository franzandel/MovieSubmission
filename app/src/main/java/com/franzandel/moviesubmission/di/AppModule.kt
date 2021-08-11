package com.franzandel.moviesubmission.di

import androidx.lifecycle.ViewModel
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.core.presentation.vm.ViewModelFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val TIMEOUT_TIME = 60L

    private val certificatePinner = CertificatePinner.Builder()
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_1")
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_2")
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_3")
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .writeTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideViewModelFactory(
        providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelFactory =
        ViewModelFactory(providerMap)
}