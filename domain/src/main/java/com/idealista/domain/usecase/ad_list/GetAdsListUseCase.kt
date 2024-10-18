package com.idealista.domain.usecase.ad_list

import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import javax.inject.Inject

class GetAdsListUseCase @Inject constructor(
    private val adsRepository: IdealistaChallengeAPIRepository
) {
    suspend operator fun invoke() = adsRepository.getAdsList()
}