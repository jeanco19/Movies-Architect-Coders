package com.jean.moviesarchitectcoders.data.datasource.movies.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jean.moviesarchitectcoders.data.database.dao.MoviesDao
import com.jean.moviesarchitectcoders.utils.movieEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieRoomDatasourceTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var moviesDao: MoviesDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun verify_when_insert_movie_then_return_the_same_movie() = runTest {
        val expectedMovie = movieEntity
        val expectedSize = 1

        moviesDao.saveMovie(expectedMovie)

        moviesDao.getMovies().first()?.let { movieEntities ->
            assertEquals(expectedSize, movieEntities.size)
            assertEquals(expectedMovie, movieEntities.first())
        }
    }

}