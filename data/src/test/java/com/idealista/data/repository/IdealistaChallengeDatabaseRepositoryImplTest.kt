package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeDatabaseDataSource
import com.idealista.data.repository.common.BaseIdealistaChallengeAPIRepositoryTest
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class IdealistaChallengeDatabaseRepositoryImplTest : BaseIdealistaChallengeAPIRepositoryTest() {

    private lateinit var repository: IdealistaChallengeDatabaseRepositoryImpl
    private val dataSource = mockk<IdealistaChallengeDatabaseDataSource>(relaxed = true)

    @Before
    override fun setUp() {
        super.setUp()
        repository = IdealistaChallengeDatabaseRepositoryImpl(dataSource, standardTestDispatcher)
    }

    @Test
    fun whenGetAllFavoritesAdsIsCalledThenCallsDataSourceAllFavoritesAds() {
        // When
        repository.getAllFavoritesAds()
        // Then
        verify { dataSource.getAllAdFavorites() }
    }

    @Test
    fun whenGetFavoriteAdByIdIsCalledThenCallsDataSourceGetAdFavorites() {
        // When
        repository.getFavoriteAdById(1)
        // Then
        verify { dataSource.getAdFavorites(1) }
    }

    @Test
    fun whenInsertFavoriteAdIsCalledThenCallsDataSourceInsertFavoriteAd() = runTest(testScheduler) {
        // When
        repository.insertFavoriteAd(mockk())
        // Then
        coVerify { dataSource.insertFavoriteAd(any()) }
    }

    @Test
    fun whenDeleteFavoriteAdByIdIsCalledThenCallsDataSourceDeleteAdFavoriteById() =
        runTest(testScheduler) {
            // When
            repository.deleteFavoriteAdById(1)
            // Then
            coVerify { dataSource.deleteAdFavoriteById(1) }
        }
}