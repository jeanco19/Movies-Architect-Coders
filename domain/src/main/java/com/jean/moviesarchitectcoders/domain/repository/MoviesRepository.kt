package com.jean.moviesarchitectcoders.domain.repository

import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    val movies: Flow<List<Movie>>

    fun getMovies(region: String): Flow<List<Movie>>

    fun getMovieById(movieId: Int): Flow<Movie>

    suspend fun saveFavorite(movie: Movie)

}