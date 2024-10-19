package com.idealista.domain.repository

import com.idealista.domain.model.ad_detail.AdFavorite
import kotlinx.coroutines.flow.Flow

interface IdealistaChallengeDatabaseRepository {
    fun getAllFavoritesAds(): Flow<List<AdFavorite>>
    fun getFavoriteAdById(id: Int): Flow<AdFavorite?>
    suspend fun insertFavoriteAd(ad: AdFavorite)
    suspend fun deleteFavoriteAdById(id: Int)
}