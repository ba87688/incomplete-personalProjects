package com.example.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.localdatabase.MovieArticle
import com.example.movies.databinding.ItemMovieInfoBinding

class MovieResultsAdapter : ListAdapter <MovieArticle, MovieResultViewHolder> (MovieResultComparator()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResultViewHolder {
        var binding = ItemMovieInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieResultViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

}