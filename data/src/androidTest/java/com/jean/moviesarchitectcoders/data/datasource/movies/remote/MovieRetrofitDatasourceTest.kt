package com.jean.moviesarchitectcoders.data.datasource.movies.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jean.moviesarchitectcoders.data.mappers.toDomain
import com.jean.moviesarchitectcoders.data.network.MoviesApiService
import com.jean.moviesarchitectcoders.utils.MockWebServerRule
import com.jean.moviesarchitectcoders.utils.expectedMovies
import com.jean.moviesarchitectcoders.utils.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MovieRetrofitDatasourceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var apiService: MoviesApiService

    lateinit var sut: MovieRemoteDatasource

    @Before
    fun setUp() {
        hiltRule.inject()
        sut = MovieRetrofitDatasource(apiService)
        mockWebServerRule.mockWebServer.enqueue(
            MockResponse().fromJson("now_playing_movies.json")
        )
    }

    @Test
    fun verify_when_call_get_movies_return_playing_movies() = runTest {
        val expectedModel = expectedMovies

        val movies = sut.getNowPlayingMovies("US")

        assertEquals(expectedModel.map { it.toDomain() }, movies)
        assertEquals(expectedModel.first().id, movies.first().id)
        assertEquals(expectedModel.first().title, movies.first().title)
    }

}