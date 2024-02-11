package com.jean.moviesarchitectcoders.data.datasource.movies.remote

import com.jean.moviesarchitectcoders.data.mappers.toDomain
import com.jean.moviesarchitectcoders.domain.models.Movie
import com.jean.moviesarchitectcoders.data.network.MoviesApiService
import javax.inject.Inject

class MovieRetrofitDatasource @Inject constructor(
    private val apiService: MoviesApiService
) : MovieRemoteDatasource {

    override suspend fun getNowPlayingMovies(region: String): List<Movie> {
        return apiService.getNowPlayingMovies(region).body()?.results?.map { movieApiModel ->
            movieApiModel.toDomain()
        } ?: listOf()
    }

}