package com.example.cursoandroid.data.remote

import com.example.cursoandroid.application.AppConstants
import com.example.cursoandroid.data.model.MovieList
import com.example.cursoandroid.repository.WebService

class RemoteMovieDataSource (private val webService: WebService){
    //suspend fun getUpcomingMovies(): MovieList = withContext(Dispatchers.IO){ webService.getUpcomingMovies(AppConstants.API_KEY)}
    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies():MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies():MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}