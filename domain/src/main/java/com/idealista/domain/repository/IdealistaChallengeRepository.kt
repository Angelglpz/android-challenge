package com.idealista.domain.repository

import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.util.ApiResponseStatus

interface IdealistaChallengeRepository {
    suspend fun getAds(): ApiResponseStatus<List<Ad>>
    suspend fun getAdDetail(): ApiResponseStatus<AdDetail>
}