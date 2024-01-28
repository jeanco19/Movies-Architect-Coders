package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<Result<List<Movie>>> {
        return moviesRepository.getFavorites()
    }

}