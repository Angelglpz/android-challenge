package com.idealista.data.datasource

import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail

internal interface IdealistaChallengeAPIDataSource {
    suspend fun getAds(): Result<List<Ad>>
    suspend fun getAdDetail(): Result<AdDetail>
}