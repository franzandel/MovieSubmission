package com.franzandel.moviesubmission.di

import android.content.Context
import android.net.Uri
import androidx.room.Room
import com.franzandel.moviesubmission.BuildConfig
import com.franzandel.moviesubmission.data.local.database.MoviesDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Franz Andel on 10/08/21.
 * Android Engineer
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val MOVIES_DB_NAME = "Movies.db"
    private const val TIMEOUT_TIME = 60L
    private val domainUrl = Uri.parse(BuildConfig.MOVIE_DB_BASE_URL).host.toString()

    private val certificatePinner = CertificatePinner.Builder()
        .add(domainUrl, BuildConfig.CERT_PINNER_1)
        .add(domainUrl, BuildConfig.CERT_PINNER_2)
        .add(domainUrl, BuildConfig.CERT_PINNER_3)
        .add(domainUrl, BuildConfig.CERT_PINNER_4)
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
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MOVIES_DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
}