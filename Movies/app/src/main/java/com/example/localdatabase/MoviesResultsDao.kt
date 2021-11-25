package com.example.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesResultsDao {
    //get data
    @Query("SELECT * FROM home_movies_page INNER JOIN movies_list ON movieUrl = imdbid")
    fun getAllHomeMovies() : Flow<List<MovieArticle>>

    //put articles in database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<MovieArticle>)

    //home page items..class inside MovieArticle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homeMovie : List<HomeMovies>)


    @Query("DELETE FROM home_movies_page")
    suspend fun deleteAllHomeMovies()



















}