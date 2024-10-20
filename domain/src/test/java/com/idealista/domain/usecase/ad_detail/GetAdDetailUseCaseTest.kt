package com.idealista.domain.usecase.ad_detail

import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAdDetailUseCaseTest {

    private lateinit var useCase: GetAdDetailUseCase
    private val adsRepository = mockk<IdealistaChallengeAPIRepository>(relaxed = true)

    @Before
    fun setUp() {
        useCase = GetAdDetailUseCase(adsRepository)
    }

    @Test
    fun whenUseCaseIsCalledThenCallsGetAdDetail() = runTest {
        // When
        useCase()
        // Then
        coVerify { adsRepository.getAdDetail() }
    }
}