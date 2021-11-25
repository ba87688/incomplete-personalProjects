package com.example.repository

import android.util.Log
import com.example.api.MoviesApi
import com.example.api.MoviesApiInterface
import com.example.localdatabase.AllMoviesDatabase
import com.example.localdatabase.MovieArticle
import javax.inject.Inject

class MoviesRepository @Inject constructor (
    private val moviesApi : MoviesApiInterface,
    private val moviesDatabase: AllMoviesDatabase
        ) {
    private val moviesDao =moviesDatabase.movieDatabaseDao()

    suspend fun getMovieResults(): List<MovieArticle>{
        val response = moviesApi.getAllMovies()
        val serverHomeMovies = response.results
        val homeMoviesResults = serverHomeMovies.map { serverHomeMovie->
            Log.d("MOVIEREPO", "getMovieResults: " +serverHomeMovie.title)
            Log.d("evan MMM", "getMovieResults: " +serverHomeMovie.genre?.get(0).toString())
            MovieArticle(
                released = serverHomeMovie.released,
                synopsis = serverHomeMovie.synopsis,
                title = serverHomeMovie.title,
                type = serverHomeMovie.type,
                bookmarked = false,
                imdbid = serverHomeMovie.imdbid,

                genre = serverHomeMovie.genre?.get(0).toString(),
//                imageUrl = if (serverHomeMovie.imageurl==null) {  null} else {serverHomeMovie.imageurl.get(0).toString()}
            )
        }
        return homeMoviesResults
    }






























}