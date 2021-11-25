package com.example.api


//#NewsArticle
data class Result(
    val genre: List<String>?,
    val imageurl: List<String>?,
    val imdbid: String,
    val imdbrating: Any,
    val released: Int,
    val synopsis: String,
    val title: String,
    val type: String
)