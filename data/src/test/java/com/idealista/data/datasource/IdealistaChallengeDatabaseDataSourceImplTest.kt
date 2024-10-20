package com.idealista.data.datasource

import com.idealista.data.common.BaseDataTest
import com.idealista.data.dao.AdFavoritesDao
import com.idealista.data.entity.AdFavoriteEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class IdealistaChallengeDatabaseDataSourceImplTest : BaseDataTest() {

    private lateinit var dataSource: IdealistaChallengeDatabaseDataSourceImpl
    private val adFavoritesDao = mockk<AdFavoritesDao>(relaxed = true)

    @Before
    override fun setUp() {
        super.setUp()
        dataSource = IdealistaChallengeDatabaseDataSourceImpl(adFavoritesDao)
    }

    @Test
    fun givenDaoDataResponseWhenGetAllAdFavoritesIsCalledThenDataIsNotEmpty() = runTest {
        // Given
        coEvery { adFavoritesDao.getAllFavorites() } returns flowOf(
            listOf(
                mockk<AdFavoriteEntity>(
                    relaxed = true
                )
            )
        )
        // When
        val result = dataSource.getAllAdFavorites().first()
        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun givenDaoExceptionResponseWhenGetAllAdFavoritesIsCalledThenDataIsEmpty() = runTest {
        // Given
        coEvery { adFavoritesDao.getAllFavorites() } returns flow { throw Exception() }
        // When
        val result = dataSource.getAllAdFavorites().first()
        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun givenDaoDataResponseWhenGetAdFavoritesIsCalledThenDataIsNotEmpty() = runTest {
        // Given
        coEvery { adFavoritesDao.getAdById(1) } returns flowOf(
            mockk<AdFavoriteEntity>(
                relaxed = true
            )
        )
        // When
        val result = dataSource.getAdFavorites(1).first()
        // Then
        assertTrue(result != null)
    }

    @Test
    fun givenDaoExceptionResponseWhenGetAdFavoritesIsCalledThenDataIsNull() = runTest {
        // Given
        coEvery { adFavoritesDao.getAdById(1) } returns flow { throw Exception() }
        // When
        val result = dataSource.getAdFavorites(1).first()
        // Then
        assertTrue(result == null)
    }

    @Test
    fun whenInsertFavoriteAdIsCalledThenDaoInsertAdIsCalled() = runTest {
        // When
        dataSource.insertFavoriteAd(mockk(relaxed = true))
        // Then
        coVerify { adFavoritesDao.insertAd(any()) }
    }

    @Test
    fun whenDeleteAdFavoriteByIdIsCalledThenDaoDeleteAdByIdIsCalled() = runTest {
        // When
        dataSource.deleteAdFavoriteById(1)
        // Then
        coVerify { adFavoritesDao.deleteAdById(1) }
    }
}