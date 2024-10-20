package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveAdFavoriteUseCaseTest {

    private lateinit var useCase: SaveAdFavoriteUseCase
    private val adDatabaseRepository = mockk<IdealistaChallengeDatabaseRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = SaveAdFavoriteUseCase(adDatabaseRepository)
    }

    @Test
    fun givenAdFavoriteWhenUseCaseIsCalledThenCallsSaveAdFavoriteAd() = runTest {
        // Given
        val adFavorite = mockk<AdFavorite>()
        // When
        useCase(adFavorite)
        // Then
        coVerify { adDatabaseRepository.insertFavoriteAd(adFavorite) }
    }
}