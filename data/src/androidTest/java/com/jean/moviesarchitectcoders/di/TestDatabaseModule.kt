package com.jean.moviesarchitectcoders.di

import android.content.Context
import androidx.room.Room
import com.jean.moviesarchitectcoders.data.database.MoviesDatabase
import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao
import com.jean.moviesarchitectcoders.data.di.DatabaseModule
import com.jean.moviesarchitectcoders.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
object TestDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            MoviesDatabase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }

}