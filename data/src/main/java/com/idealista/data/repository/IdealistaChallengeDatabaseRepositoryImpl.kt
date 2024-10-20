package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeDatabaseDataSource
import com.idealista.domain.model.ad_detail.AdFavorite
import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class IdealistaChallengeDatabaseRepositoryImpl @Inject constructor(
    private val databaseDataSource: IdealistaChallengeDatabaseDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : IdealistaChallengeDatabaseRepository {

    override fun getAllFavoritesAds(): Flow<List<AdFavorite>> {
        return databaseDataSource.getAllAdFavorites()
    }

    override fun getFavoriteAdById(id: Int): Flow<AdFavorite?> {
        return databaseDataSource.getAdFavorites(id)
    }

    override suspend fun insertFavoriteAd(ad: AdFavorite) {
        withContext(ioDispatcher) {
            databaseDataSource.insertFavoriteAd(ad)
        }
    }

    override suspend fun deleteFavoriteAdById(id: Int) {
        withContext(ioDispatcher) {
            databaseDataSource.deleteAdFavoriteById(id)
        }
    }
}