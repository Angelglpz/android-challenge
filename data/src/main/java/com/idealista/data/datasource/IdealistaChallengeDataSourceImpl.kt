package com.idealista.data.datasource

import com.idealista.data.api.AdApiService
import com.idealista.data.dto.ad.AdResponse
import com.idealista.data.dto.ad_detail.AdDetailResponse

internal class IdealistaChallengeDataSourceImpl(
    private val adApiService: AdApiService
) : IdealistaChallengeDataSource {
    override suspend fun getAds(): List<AdResponse> {
        return adApiService.getAds()
    }

    override suspend fun getAdDetail(): AdDetailResponse {
        return adApiService.getAdDetail()
    }
}