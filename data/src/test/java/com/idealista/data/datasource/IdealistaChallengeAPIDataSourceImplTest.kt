package com.idealista.data.datasource

import com.idealista.data.api.AdApiService
import com.idealista.data.common.BaseDataTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class IdealistaChallengeAPIDataSourceImplTest : BaseDataTest() {

    private lateinit var dataSource: IdealistaChallengeAPIDataSourceImpl
    private val apiService = mockk<AdApiService>(relaxed = true)

    @Before
    override fun setUp() {
        super.setUp()
        dataSource = IdealistaChallengeAPIDataSourceImpl(apiService)
    }

    @Test
    fun givenApiSuccessResponseWhenGetAdsIsCalledThenReturnResultSuccess() = runTest {
        // Given
        coEvery { apiService.getAds() } returns mockk(relaxed = true)
        // When
        val result = dataSource.getAds()
        // Then
        assertTrue(result.isSuccess)
    }

    @Test
    fun givenApiFailureResponseWhenGetAdsIsCalledThenReturnResultFailure() = runTest {
        // Given
        coEvery { apiService.getAds() } throws Exception()
        // When
        val result = dataSource.getAds()
        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun givenApiSuccessResponseWhenGetAdDetailIsCalledThenReturnResultSuccess() = runTest {
        // Given
        coEvery { apiService.getAdDetail() } returns mockk(relaxed = true)
        // When
        val result = dataSource.getAdDetail()
        // Then
        assertTrue(result.isSuccess)
    }

    @Test
    fun givenApiFailureResponseWhenGetAdDetailIsCalledThenReturnResultFailure() = runTest {
        // Given
        coEvery { apiService.getAdDetail() } throws Exception()
        // When
        val result = dataSource.getAdDetail()
        // Then
        assertTrue(result.isFailure)
    }
}