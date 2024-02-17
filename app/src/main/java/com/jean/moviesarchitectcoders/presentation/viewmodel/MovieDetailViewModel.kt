package com.jean.moviesarchitectcoders.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.usecases.GetMovieByIdUseCase
import com.jean.moviesarchitectcoders.domain.usecases.SaveFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val saveFavoriteMoviesUseCase: SaveFavoriteMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            getMovieByIdUseCase(movieId)
                .onSuccess { movieResult ->
                    movieResult.collect { movie ->
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                movie = movie,
                                hasError = false
                            )
                        }
                    }
                }
                .onFailure {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                }
        }
    }

    fun saveFavorite(movie: Movie) {
        viewModelScope.launch {
            saveFavoriteMoviesUseCase(movie)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val movie: Movie? = null,
        val hasError: Boolean = false
    )

}