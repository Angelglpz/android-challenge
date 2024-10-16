package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeDataSource
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.repository.IdealistaChallengeRepository
import com.idealista.domain.util.ApiResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class IdealistaChallengeRepositoryImpl @Inject constructor(
    private val dataSource: IdealistaChallengeDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : IdealistaChallengeRepository {
    override suspend fun getAdsList(): ApiResponseStatus<List<Ad>> {
        return withContext(ioDispatcher) {
            dataSource.getAds()
        }
    }

    override suspend fun getAdDetail(): ApiResponseStatus<AdDetail> {
        return withContext(ioDispatcher) {
            dataSource.getAdDetail()
        }
    }
}