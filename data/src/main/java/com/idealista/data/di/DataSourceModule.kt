package com.idealista.data.di

import com.idealista.data.datasource.IdealistaChallengeDataSource
import com.idealista.data.datasource.IdealistaChallengeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun bindsIdealistaChallengeDataSource(impl: IdealistaChallengeDataSourceImpl): IdealistaChallengeDataSource
}