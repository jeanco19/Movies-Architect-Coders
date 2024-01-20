package com.jean.moviesarchitectcoders.data.database

import androidx.room.RoomDatabase
import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao

abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}