package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val regionRepository: RegionRepository
) {

    suspend operator fun invoke(): Flow<List<Movie>> {
        val region = regionRepository.findLastRegion()
        return moviesRepository.getMovies(region)
    }

}