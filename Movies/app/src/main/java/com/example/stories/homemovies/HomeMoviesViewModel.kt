package com.example.stories.homemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdatabase.MovieArticle
import com.example.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) :ViewModel() {

    //create a flow to fragment. give it a empty list
    private val homeMovieResultsFlow = MutableStateFlow<List<MovieArticle>>(emptyList())
    val homeResults :Flow<List<MovieArticle>> = homeMovieResultsFlow

    //get the data from retrofit as soon as app starts.

    init{
        viewModelScope.launch {
            val news =repository.getMovieResults()
            //fill the empty list when task is done
            homeMovieResultsFlow.value = news
        }
    }
}