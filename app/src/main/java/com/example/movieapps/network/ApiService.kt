package com.example.movieapps.network

import com.example.movieapps.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<MovieResponse>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ) : Call<MovieResponse>
}