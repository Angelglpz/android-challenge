package com.idealista.data.api

import com.idealista.data.dto.ad.AdResponse
import com.idealista.data.dto.ad_detail.AdDetailResponse
import retrofit2.http.GET

interface AdApiService {
    @GET("list.json")
    suspend fun getAds(): List<AdResponse>

    @GET("detail.json")
    suspend fun getAdDetail(): AdDetailResponse
}