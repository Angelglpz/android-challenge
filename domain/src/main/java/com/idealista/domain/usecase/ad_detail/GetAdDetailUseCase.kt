package com.idealista.domain.usecase.ad_detail

import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import javax.inject.Inject

class GetAdDetailUseCase @Inject constructor(
    private val adsRepository: IdealistaChallengeAPIRepository
) {
    suspend operator fun invoke() = adsRepository.getAdDetail()
}