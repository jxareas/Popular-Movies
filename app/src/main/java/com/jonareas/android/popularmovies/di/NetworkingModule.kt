package com.jonareas.android.popularmovies.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jonareas.android.popularmovies.network.service.ActorService
import com.jonareas.android.popularmovies.network.service.MovieService
import com.jonareas.android.popularmovies.network.service.TvShowsService
import com.jonareas.android.popularmovies.utils.TMDb_API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val APPLICATION_JSON : String = "application/json"

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = BODY
        }

    @Provides
    @Singleton
    fun provideInterceptor() : Interceptor =
        Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", TMDb_API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)

        }

    @Provides
    @Singleton
    fun provideOkHttpClient(authenticationInterceptor : Interceptor, loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideJson() : Json =
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideConverter(json : Json) : Converter.Factory =
        json.asConverterFactory(APPLICATION_JSON.toMediaType())

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converter : Converter.Factory) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit) : MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideTvShowService(retrofit : Retrofit) : TvShowsService =
        retrofit.create(TvShowsService::class.java)

    @Provides
    @Singleton
    fun provideActorService(retrofit : Retrofit) : ActorService =
        retrofit.create(ActorService::class.java)


}
