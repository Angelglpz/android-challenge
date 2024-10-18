package com.idealista.domain.usecase.ad_favorite

import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import javax.inject.Inject

class DeleteAdFavoriteUseCase @Inject constructor(
    private val adDatabaseRepository: IdealistaChallengeDatabaseRepository
) {
    suspend operator fun invoke(id: Int) = adDatabaseRepository.deleteFavoriteAdById(id)
}