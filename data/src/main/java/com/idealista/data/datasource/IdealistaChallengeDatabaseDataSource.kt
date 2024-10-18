package com.idealista.data.datasource

import com.idealista.domain.model.ad_detail.AdFavorite
import kotlinx.coroutines.flow.Flow

interface IdealistaChallengeDatabaseDataSource {
    fun getAllAdFavorites(): Flow<Result<List<AdFavorite>>>
    fun getAdFavorites(id: String): Flow<Result<AdFavorite?>>
    suspend fun insertFavoriteAd(adFavorite: AdFavorite)
    suspend fun deleteAdFavoriteById(id: Int)
}