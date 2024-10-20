package com.idealista.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idealista.data.dao.AdFavoritesDao
import com.idealista.data.entity.AdFavoriteEntity

@Database(entities = [AdFavoriteEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun adFavoriteDao(): AdFavoritesDao
}