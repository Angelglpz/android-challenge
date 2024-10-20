package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeAPIDataSource
import com.idealista.data.repository.common.BaseIdealistaChallengeAPIRepositoryTest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class IdealistaChallengeAPIRepositoryImplTest : BaseIdealistaChallengeAPIRepositoryTest() {

    private lateinit var repository: IdealistaChallengeAPIRepositoryImpl
    private val dataSource = mockk<IdealistaChallengeAPIDataSource>(relaxed = true)

    @Before
    override fun setUp() {
        super.setUp()
        repository = IdealistaChallengeAPIRepositoryImpl(dataSource, standardTestDispatcher)
    }

    @Test
    fun whenGetAdsListIsCalledThenCallsDataSourceGetAds() = runTest(testScheduler) {
        // When
        repository.getAdsList()
        // Then
        coVerify { dataSource.getAds() }
    }

    @Test
    fun whenGetAdDetailIsCalledThenCallsDataSourceGetAdDetail() = runTest(testScheduler) {
        // When
        repository.getAdDetail()
        // Then
        coVerify { dataSource.getAdDetail() }
    }
}