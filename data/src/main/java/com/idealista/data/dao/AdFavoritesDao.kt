package com.idealista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idealista.data.entity.AdFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AdFavoritesDao {

    @Query("SELECT * FROM ad_favorite")
    fun getAllFavorites(): Flow<List<AdFavoriteEntity>>

    @Query("SELECT * FROM ad_favorite WHERE id = :id")
    fun getAdById(id: Int): Flow<AdFavoriteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAd(ad: AdFavoriteEntity)

    @Query("DELETE FROM ad_favorite WHERE id = :id")
    suspend fun deleteAdById(id: Int)
}