package com.jean.moviesarchitectcoders.data.repository

import com.jean.moviesarchitectcoders.data.datasource.location.StubLocationPlayServicesDatasource
import com.jean.moviesarchitectcoders.data.utils.CoroutineTestRule
import com.jean.moviesarchitectcoders.domain.repository.RegionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegionRepositoryTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var locationStub: StubLocationPlayServicesDatasource
    private lateinit var sut: RegionRepository

    @Before
    fun setUp() {
        locationStub = StubLocationPlayServicesDatasource()
        sut = RegionRepositoryImpl(locationStub)
    }

    @Test
    fun `verify when the region from datasource is null, then return the default region`() = runTest {
        val expectedRegion = "US"

        val region = sut.findLastRegion()

        assertNotNull(region)
        assertEquals(expectedRegion, region)
    }

}