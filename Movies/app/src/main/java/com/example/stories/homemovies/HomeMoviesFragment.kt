package com.example.stories.homemovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesHomeBinding
import com.example.shared.MovieResultsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeMoviesFragment : Fragment (R.layout.fragment_movies_home) {

    //get viewModel from homeMoviesViewModel into the fragment
    private val viewModel : HomeMoviesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //to use viewbinding
        val binding = FragmentMoviesHomeBinding.bind(view)

        val movieResultsAdapter = MovieResultsAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = movieResultsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

            }
            //collect data from flow
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.homeResults.collect { movie->
                    movieResultsAdapter.submitList(movie)
                }
            }

        }



















    }
}