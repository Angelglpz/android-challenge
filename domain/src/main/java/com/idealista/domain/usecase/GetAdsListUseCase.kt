package com.idealista.domain.usecase

import com.idealista.domain.repository.IdealistaChallengeRepository
import javax.inject.Inject

class GetAdsListUseCase @Inject constructor(
    private val adsRepository: IdealistaChallengeRepository
) {
    suspend operator fun invoke() = adsRepository.getAdsList()
}