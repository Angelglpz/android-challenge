package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeDatabaseDataSource
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class IdealistaChallengeDatabaseRepositoryImpl @Inject constructor(
    private val databaseDataSource: IdealistaChallengeDatabaseDataSource
) : IdealistaChallengeDatabaseRepository {

    override fun getAllFavoritesAds(): Flow<Result<List<AdFavorite>>> {
        return databaseDataSource.getAllAdFavorites()
    }

    override fun getFavoriteAdById(id: Int): Flow<Result<AdFavorite?>> {
        return databaseDataSource.getAdFavorites(id)
    }

    override suspend fun insertFavoriteAd(ad: AdFavorite) {
        databaseDataSource.insertFavoriteAd(ad)
    }

    override suspend fun deleteFavoriteAdById(id: Int) {
        databaseDataSource.deleteAdFavoriteById(id)
    }
}