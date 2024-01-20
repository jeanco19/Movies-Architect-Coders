package com.jean.moviesarchitectcoders.data.mappers

import com.jean.moviesarchitectcoders.data.database.entity.MovieEntity
import com.jean.moviesarchitectcoders.data.models.Movie
import com.jean.moviesarchitectcoders.data.network.models.MovieApiModel

fun MovieApiModel.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        isFavorite = false
    )
}


fun List<MovieEntity>?.toDomain(): List<Movie> {
    return this?.map { movieEntity ->
        movieEntity.toDomain()
    } ?: listOf()
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )
}