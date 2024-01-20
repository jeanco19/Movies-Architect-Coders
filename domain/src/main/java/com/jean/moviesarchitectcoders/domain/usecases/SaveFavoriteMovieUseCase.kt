package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import javax.inject.Inject

class SaveFavoriteMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movie: Movie) {
        moviesRepository.saveFavorite(movie)
    }

}