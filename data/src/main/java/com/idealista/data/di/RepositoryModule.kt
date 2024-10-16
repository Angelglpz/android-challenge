package com.idealista.data.di

import com.idealista.data.repository.IdealistaChallengeRepositoryImpl
import com.idealista.domain.repository.IdealistaChallengeRepository
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
    fun bindsIdealistaChallengeRepository(idealistaChallengeRepository: IdealistaChallengeRepositoryImpl): IdealistaChallengeRepository
}