package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import javax.inject.Inject

class GetAllFavoriteAdsUseCase @Inject constructor(
    private val adDatabaseRepository: IdealistaChallengeDatabaseRepository
) {
    operator fun invoke() = adDatabaseRepository.getAllFavoritesAds()
}