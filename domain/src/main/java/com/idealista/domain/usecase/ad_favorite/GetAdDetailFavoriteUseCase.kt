package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import javax.inject.Inject

class GetAdDetailFavoriteUseCase @Inject constructor(
    private val adDatabaseRepository: IdealistaChallengeDatabaseRepository
) {
    operator fun invoke(id: Int) = adDatabaseRepository.getFavoriteAdById(id)
}