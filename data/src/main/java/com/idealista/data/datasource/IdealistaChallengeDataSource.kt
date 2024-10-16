package com.idealista.data.datasource

import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.util.ApiResponseStatus

internal interface IdealistaChallengeDataSource {

    suspend fun getAds(): ApiResponseStatus<List<Ad>>
    suspend fun getAdDetail(): ApiResponseStatus<AdDetail>

}