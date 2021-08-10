package com.franzandel.moviesubmission.di

import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.data.remote.network.MoviesNetworkService
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

object AppModule {
    private const val TIMEOUT_TIME = 60L

    private val certificatePinner = CertificatePinner.Builder()
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_1")
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_2")
        .add(BuildConfig.MOVIE_DB_BASE_URL, "BuildConfig.CERT_PINNER_3")
        .build()

    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .writeTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
            .build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideMoviesNetworkService(retrofit: Retrofit): MoviesNetworkService =
        retrofit.create(MoviesNetworkService::class.java)
}