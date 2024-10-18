package com.idealista.data.di

import com.idealista.data.repository.IdealistaChallengeAPIRepositoryImpl
import com.idealista.data.repository.IdealistaChallengeDatabaseRepositoryImpl
import com.idealista.domain.repository.IdealistaChallengeAPIRepository
import com.idealista.domain.repository.IdealistaChallengeDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsIdealistaChallengeAPIRepository(idealistaChallengeRepository: IdealistaChallengeAPIRepositoryImpl): IdealistaChallengeAPIRepository

    @Binds
    @Singleton
    fun bindsIdealistaChallengeDatabaseRepository(idealistaChallengeDatabaseRepository: IdealistaChallengeDatabaseRepositoryImpl): IdealistaChallengeDatabaseRepository
}