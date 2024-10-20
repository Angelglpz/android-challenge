package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllFavoriteAdsUseCaseTest {

    private lateinit var useCase: GetAllFavoriteAdsUseCase
    private val adDatabaseRepository = mockk<IdealistaChallengeDatabaseRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = GetAllFavoriteAdsUseCase(adDatabaseRepository)
    }

    @Test
    fun whenUseCaseIsCalledThenCallsGetAllFavoritesAds() = runTest {
        // When
        useCase()
        // Then
        coVerify { adDatabaseRepository.getAllFavoritesAds() }
    }
}