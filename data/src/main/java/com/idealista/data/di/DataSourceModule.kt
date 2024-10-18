package com.idealista.data.di

import com.idealista.data.datasource.IdealistaChallengeAPIDataSource
import com.idealista.data.datasource.IdealistaChallengeAPIDataSourceImpl
import com.idealista.data.datasource.IdealistaChallengeDatabaseDataSource
import com.idealista.data.datasource.IdealistaChallengeDatabaseDataSourceImpl
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
    fun bindsIdealistaChallengeAPIDataSource(impl: IdealistaChallengeAPIDataSourceImpl): IdealistaChallengeAPIDataSource

    @Binds
    @Singleton
    fun bindsIdealistaChallengeDatabaseDataSource(impl: IdealistaChallengeDatabaseDataSourceImpl): IdealistaChallengeDatabaseDataSource
}