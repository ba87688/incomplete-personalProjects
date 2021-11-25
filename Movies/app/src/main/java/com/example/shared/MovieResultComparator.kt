package com.example.shared

import androidx.recyclerview.widget.DiffUtil
import com.example.localdatabase.MovieArticle

class MovieResultComparator :DiffUtil.ItemCallback<MovieArticle> (){
    override fun areItemsTheSame(oldItem: MovieArticle, newItem: MovieArticle): Boolean =
        oldItem.imdbid == newItem.imdbid

    override fun areContentsTheSame(oldItem: MovieArticle, newItem: MovieArticle): Boolean =
        oldItem == newItem



}