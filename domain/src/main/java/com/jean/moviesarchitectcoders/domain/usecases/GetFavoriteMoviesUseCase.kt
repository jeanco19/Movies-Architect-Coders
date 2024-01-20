package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return moviesRepository.movies.map { movies ->
            movies.filter { movie -> movie.isFavorite }
        }
    }

}