package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(movieId: Int): Flow<Result<Movie>> {
        return moviesRepository.getMovieById(movieId)
    }

}