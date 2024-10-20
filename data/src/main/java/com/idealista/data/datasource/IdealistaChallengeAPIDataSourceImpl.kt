package com.idealista.data.datasource

import com.idealista.data.api.AdApiService
import com.idealista.data.mapper.ad.toDomain
import com.idealista.data.mapper.ad_detail.toDomain
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import javax.inject.Inject

class IdealistaChallengeAPIDataSourceImpl @Inject constructor(
    private val adApiService: AdApiService
) : IdealistaChallengeAPIDataSource {

    override suspend fun getAds(): Result<List<Ad>> {
        return try {
            Result.success(adApiService.getAds().map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAdDetail(): Result<AdDetail> {
        return try {
            Result.success(adApiService.getAdDetail().toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}