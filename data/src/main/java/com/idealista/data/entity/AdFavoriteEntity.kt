package com.idealista.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ad_favorite")
data class AdFavoriteEntity(
    @PrimaryKey val id: Int,
    val isFavorite: Boolean,
    val date: Long
)
