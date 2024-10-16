package com.idealista.data.datasource

import com.idealista.data.api.AdApiService
import com.idealista.data.mapper.ad.toDomain
import com.idealista.data.mapper.ad_detail.toDomain
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.util.ApiResponseStatus
import javax.inject.Inject

internal class IdealistaChallengeDataSourceImpl @Inject constructor(
    private val adApiService: AdApiService
) : IdealistaChallengeDataSource {

    override suspend fun getAds(): ApiResponseStatus<List<Ad>> {
        return try {
            ApiResponseStatus.Success(adApiService.getAds().map { it.toDomain() })
        } catch (e: Exception) {
            ApiResponseStatus.Error(e)
        }
    }

    override suspend fun getAdDetail(): ApiResponseStatus<AdDetail> {
        return try {
            ApiResponseStatus.Success(adApiService.getAdDetail().toDomain())
        } catch (e: Exception) {
            ApiResponseStatus.Error(e)
        }
    }
}