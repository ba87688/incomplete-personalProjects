package com.example.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies_list")
data class MovieArticle (

    val genre: String,
//    val imageurl: List<String>,
    @PrimaryKey val imdbid: String,
//    val imdbrating: Any,
    val released: Int,
    val synopsis: String?,
    val title: String?,
    val type: String?,
    //made
//    val imageUrl :String?,

    val bookmarked : Boolean,
    val updatedAt:Long = System.currentTimeMillis()
        )

@Entity(tableName = "home_movies_page")
data class HomeMovies(
    var movieUrl :String,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
)