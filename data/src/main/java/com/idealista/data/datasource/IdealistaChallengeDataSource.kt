package com.idealista.data.datasource

import com.idealista.data.dto.ad.AdResponse
import com.idealista.data.dto.ad_detail.AdDetailResponse

internal interface IdealistaChallengeDataSource {

    suspend fun getAds(): List<AdResponse>
    suspend fun getAdDetail(): AdDetailResponse

}