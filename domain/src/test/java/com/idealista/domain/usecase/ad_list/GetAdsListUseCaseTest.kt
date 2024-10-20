package com.idealista.domain.usecase.ad_list

import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAdsListUseCaseTest {

    private lateinit var useCase: GetAdsListUseCase
    private val adsRepository = mockk<IdealistaChallengeAPIRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = GetAdsListUseCase(adsRepository)
    }

    @Test
    fun whenUseCaseIsCalledThenCallsGetAdsList() = runTest {
        // When
        useCase()
        // Then
        coVerify { adsRepository.getAdsList() }
    }
}