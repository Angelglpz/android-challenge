package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import javax.inject.Inject

class SaveAdFavoriteUseCase @Inject constructor(
    private val adDatabaseRepository: IdealistaChallengeDatabaseRepository
) {
    suspend operator fun invoke(ad: AdFavorite) =
        adDatabaseRepository.insertFavoriteAd(ad)
}