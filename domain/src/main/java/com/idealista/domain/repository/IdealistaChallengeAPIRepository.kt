package com.idealista.domain.repository

import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail

interface IdealistaChallengeAPIRepository {
    suspend fun getAdsList(): Result<List<Ad>>
    suspend fun getAdDetail(): Result<AdDetail>
}