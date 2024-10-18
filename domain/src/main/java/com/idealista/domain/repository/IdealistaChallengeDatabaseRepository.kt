package com.idealista.domain.repository

import com.idealista.domain.model.ad_detail.AdFavorite
import kotlinx.coroutines.flow.Flow

interface IdealistaChallengeDatabaseRepository {
    fun getAllFavoritesAds(): Flow<Result<List<AdFavorite>>>
    fun getFavoriteAdById(id: String): Flow<Result<AdFavorite?>>
    suspend fun insertFavoriteAd(ad: AdFavorite)
    suspend fun deleteFavoriteAdById(id: Int)
}