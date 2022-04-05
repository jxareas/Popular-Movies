package com.jonareas.android.popularmovies.network

import com.jonareas.android.popularmovies.network.service.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApi {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val TMDb_API_KEY = "a13c5166b4b7d0c6edee4e9e9dc71aaa"

    private val authenticationInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", TMDb_API_KEY)
            .build()

        val newRequest = chain
            .request()
            .newBuilder()
            .url(newUrl).build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient
        .Builder().addInterceptor(authenticationInterceptor).build()

    private val converter = GsonConverterFactory.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(converter)
        .build()

    val movieService = retrofit.create(MovieService::class.java)



}
