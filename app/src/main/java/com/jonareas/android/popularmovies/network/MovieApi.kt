package com.jonareas.android.popularmovies.network

import okhttp3.Interceptor

object MovieApi {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val TMDb_API_KEY = "a13c5166b4b7d0c6edee4e9e9dc71aaa"

    private val authInterceptor : Interceptor = Interceptor { chain ->
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











}