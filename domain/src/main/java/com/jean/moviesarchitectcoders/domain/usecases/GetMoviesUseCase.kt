package com.jean.moviesarchitectcoders.domain.usecases

import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.domain.repository.MoviesRepository
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import com.jean.moviesarchitectcoders.domain.utils.Constants.DEFAULT_REGION
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val regionRepository: RegionRepository
) {

    suspend operator fun invoke(hasPermissions: Boolean): Result<Flow<List<Movie>>> {
        val region = if (hasPermissions) regionRepository.findLastRegion() else DEFAULT_REGION
        return moviesRepository.getMovies(region)
    }

}