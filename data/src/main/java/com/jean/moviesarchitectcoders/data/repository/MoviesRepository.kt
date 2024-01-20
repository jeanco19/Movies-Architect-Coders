package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(region: String): Flow<List<Movie>>

    suspend fun saveFavorite(movie: Movie)

}