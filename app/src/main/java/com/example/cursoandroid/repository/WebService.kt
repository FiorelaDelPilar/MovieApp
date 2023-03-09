package com.example.cursoandroid.repository

import com.example.cursoandroid.application.AppConstants
import com.example.cursoandroid.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")apiKey:String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key")apiKey:String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key")apiKey:String): MovieList
}

object RetrofitClient{
    val webService by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}