package com.idealista.data.datasource

import com.idealista.data.dao.AdFavoritesDao
import com.idealista.data.mapper.ad_favorite.toDomain
import com.idealista.data.mapper.ad_favorite.toEntity
import com.idealista.domain.model.ad_detail.AdFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class IdealistaChallengeDatabaseDataSourceImpl @Inject constructor(
    private val adFavoritesDao: AdFavoritesDao
) : IdealistaChallengeDatabaseDataSource {

    override fun getAllAdFavorites(): Flow<Result<List<AdFavorite>>> {
        return adFavoritesDao.getAllFavorites().map {
            Result.success(it.map { adFavoriteEntity -> adFavoriteEntity.toDomain() })
        }
    }

    override fun getAdFavorites(id: Int): Flow<Result<AdFavorite?>> {
        return adFavoritesDao.getAdById(id).map {
            if (it != null) {
                Result.success(it.toDomain())
            } else {
                Result.failure(Exception("Ad not found"))
            }
        }
    }

    override suspend fun insertFavoriteAd(adFavorite: AdFavorite) {
        adFavoritesDao.insertAd(adFavorite.toEntity())
    }

    override suspend fun deleteAdFavoriteById(id: Int) {
        adFavoritesDao.deleteAdById(id)
    }
}