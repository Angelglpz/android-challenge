package com.idealista.data.repository

import com.idealista.data.datasource.IdealistaChallengeAPIDataSource
import com.idealista.domain.model.ad.Ad
import com.idealista.domain.model.ad_detail.AdDetail
import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class IdealistaChallengeAPIRepositoryImpl @Inject constructor(
    private val dataSource: IdealistaChallengeAPIDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : IdealistaChallengeAPIRepository {
    override suspend fun getAdsList(): Result<List<Ad>> {
        return withContext(ioDispatcher) {
            dataSource.getAds()
        }
    }

    override suspend fun getAdDetail(): Result<AdDetail> {
        return withContext(ioDispatcher) {
            dataSource.getAdDetail()
        }
    }
}