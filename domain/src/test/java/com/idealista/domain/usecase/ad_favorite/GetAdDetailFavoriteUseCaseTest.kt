package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class GetAdDetailFavoriteUseCaseTest {

    private lateinit var useCase: GetAdDetailFavoriteUseCase
    private val adDatabaseRepository = mockk<IdealistaChallengeDatabaseRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = GetAdDetailFavoriteUseCase(adDatabaseRepository)
    }

    @Test
    fun whenUseCaseIsCalledThenCallsGetAdDetailFavoriteAdById() {
        // When
        useCase(1)
        // Then
        coVerify { adDatabaseRepository.getFavoriteAdById(1) }
    }
}