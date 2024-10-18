package com.idealista.data.di

import android.content.Context
import androidx.room.Room
import com.idealista.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAdDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "idealista.db"
        ).build()
    }

    @Provides
    fun provideAdFavoritesDao(database: AppDatabase) = database.adFavoriteDao()
}