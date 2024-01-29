package com.jean.moviesarchitectcoders.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.jean.moviesarchitectcoders.R
import com.jean.moviesarchitectcoders.databinding.FragmentMovieDetailBinding
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.presentation.viewmodel.MovieDetailViewModel
import com.jean.moviesarchitectcoders.utils.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private var currentMovie: Movie? = null

    private val viewModel: MovieDetailViewModel by viewModels()
    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieById(safeArgs.movieId)
        setupObserver()
        setupListeners()
    }

    private fun setupObserver() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) { uiState -> handleUiState(uiState) }
    }

    private fun setupListeners() {
        binding.fabFavorite.setOnClickListener {
            currentMovie?.let { movie ->
                viewModel.saveFavorite(movie)
            }
        }
    }

    private fun handleUiState(uiState: MovieDetailViewModel.UiState) = with(binding) {
        currentMovie = uiState.movie
        progressCircular.isVisible = uiState.isLoading
        llDetailError.isVisible = uiState.hasError
        clDetail.isVisible = !uiState.hasError
        ivPoster.load(uiState.movie?.posterPath)
        tvTitle.text = uiState.movie?.title
        tvDescription.text = uiState.movie?.overview
        tvReleaseDate.text = uiState.movie?.releaseDate
        tvAverage.text = uiState.movie?.voteAverage.toString()
        val favoriteResource = if (uiState.movie?.isFavorite == true) R.drawable.favorite_white_24 else R.drawable.favorite_border_white_24
        fabFavorite.setImageResource(favoriteResource)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}