package com.idealista.data.datasource

import android.util.Log
import com.idealista.data.dao.AdFavoritesDao
import com.idealista.data.mapper.ad_favorite.toDomain
import com.idealista.data.mapper.ad_favorite.toEntity
import com.idealista.domain.model.ad_detail.AdFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class IdealistaChallengeDatabaseDataSourceImpl @Inject constructor(
    private val adFavoritesDao: AdFavoritesDao
) : IdealistaChallengeDatabaseDataSource {

    override fun getAllAdFavorites(): Flow<List<AdFavorite>> {
        return adFavoritesDao.getAllFavorites().map {
            it.map { adFavoriteEntity -> adFavoriteEntity.toDomain() }
        }.catch { exception ->
            Log.d(
                IdealistaChallengeDatabaseDataSourceImpl::class.simpleName,
                "Error getting all ad favorites",
                exception
            )
            emit(emptyList())
        }
    }

    override fun getAdFavorites(id: Int): Flow<AdFavorite?> {
        return adFavoritesDao.getAdById(id).map {
            it?.toDomain()
        }.catch { exception ->
            Log.d(
                IdealistaChallengeDatabaseDataSourceImpl::class.simpleName,
                "Error getting ad favorite by id: $id",
                exception
            )
            emit(null)
        }
    }

    override suspend fun insertFavoriteAd(adFavorite: AdFavorite) {
        adFavoritesDao.insertAd(adFavorite.toEntity())
    }

    override suspend fun deleteAdFavoriteById(id: Int) {
        adFavoritesDao.deleteAdById(id)
    }
}