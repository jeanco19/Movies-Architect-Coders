package com.jean.moviesarchitectcoders.domain.repository

import com.jean.moviesarchitectcoders.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(region: String): Result<Flow<List<Movie>>>
    suspend fun getFavorites(): Result<Flow<List<Movie>>>

    suspend fun getMovieById(movieId: Int): Result<Flow<Movie>>

    suspend fun saveFavorite(movie: Movie)

}