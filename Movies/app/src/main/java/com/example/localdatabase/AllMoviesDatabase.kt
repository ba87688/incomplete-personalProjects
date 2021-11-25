package com.example.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieArticle::class,HomeMovies::class], version = 1)
abstract class AllMoviesDatabase :RoomDatabase() {

    abstract fun movieDatabaseDao() :MoviesResultsDao
}