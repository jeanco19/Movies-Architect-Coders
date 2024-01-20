package com.jean.moviesarchitectcoders.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.moviesarchitectcoders.data.utils.Constants.MOVIES_TABLE

@Entity(tableName = MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val isFavorite: Boolean
)