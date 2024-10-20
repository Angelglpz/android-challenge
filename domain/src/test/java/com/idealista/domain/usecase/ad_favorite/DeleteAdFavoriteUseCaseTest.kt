package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteAdFavoriteUseCaseTest {

    private lateinit var useCase: DeleteAdFavoriteUseCase
    private val adDatabaseRepository = mockk<IdealistaChallengeDatabaseRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = DeleteAdFavoriteUseCase(adDatabaseRepository)
    }

    @Test
    fun whenUseCaseIsCalledThenCallsDeleteAdFavoriteAdById() = runTest {
        // When
        useCase(1)
        // Then
        coVerify { adDatabaseRepository.deleteFavoriteAdById(1) }
    }
}