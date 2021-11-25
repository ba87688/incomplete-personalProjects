package com.example.api

import com.example.movies.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApiInterface {

    companion object{
        const val BASE_URL = "https://ott-details.p.rapidapi.com/"
        const val API_KEY = BuildConfig.MOVIES_API_ACCESS_KEY
    }
    @Headers("rapidapi-key: $API_KEY")
    @GET("advancedsearch?start_year=2019&end_year=2021")
    suspend fun getAllMovies(): MoviesApi


    @Headers("rapidapi-key: $API_KEY")
    @GET("search")
    suspend fun searchMovies(
        @Query("title") query : String,
        @Query("page") currentPage : Int,
        @Query("pageSize") pageSize : Int,
    ):MoviesApi























}