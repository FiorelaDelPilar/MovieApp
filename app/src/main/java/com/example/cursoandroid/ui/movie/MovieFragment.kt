package com.example.cursoandroid.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.cursoandroid.R
import com.example.cursoandroid.core.Resource
import com.example.cursoandroid.data.local.AppDatabase
import com.example.cursoandroid.data.local.LocalMovieDataSource
import com.example.cursoandroid.data.model.Movie
import com.example.cursoandroid.data.remote.RemoteMovieDataSource
import com.example.cursoandroid.databinding.FragmentMovieBinding
import com.example.cursoandroid.presentation.movieViewModel
import com.example.cursoandroid.presentation.movieViewModelFactory
import com.example.cursoandroid.repository.MovieRepositoryImpl
import com.example.cursoandroid.repository.RetrofitClient
import com.example.cursoandroid.ui.movie.adapters.MovieAdapter
import com.example.cursoandroid.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.cursoandroid.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.cursoandroid.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<movieViewModel> {
        movieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()


        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    //Log.d("LiveData", "Loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    //Log.d("LiveData", "Upcoming: ${result.data.first} \n \n " + "TopRated: ${result.data.second} \n \n" + "Popular: ${result.data.third}"
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveDataError", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}