package com.example.shared

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.localdatabase.MovieArticle
import com.example.movies.R
import com.example.movies.databinding.ItemMovieInfoBinding

class MovieResultViewHolder(
    private val binding: ItemMovieInfoBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(movie : MovieArticle){
        binding.apply {

//         ////   Glide.with(itemView).load(movie.imageurl[0])
//                .error(R.drawable.image_placeholder)
//                .into(imageView)

            textViewTitle.text =movie.title ?: ""

            textTitleRelease.text = movie.released.toString() ?:""
            textTitleGenre.text = movie.genre ?:""


            imageViewBookmark.setImageResource(
                when {
                    movie.bookmarked -> R.drawable.ic_bookmarks
                    else -> R.drawable.bookmark_unselected
                }
            )
        }

    }
}