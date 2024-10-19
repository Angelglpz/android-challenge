package com.idealista.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.idealista.data.util.Constants

@Entity(tableName = Constants.TABLE_AD_FAVORITE)
data class AdFavoriteEntity(
    @PrimaryKey val id: Int,
    val isFavorite: Boolean,
    val date: Long
)
