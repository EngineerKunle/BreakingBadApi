package com.ekotech.breakingbad.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Singleton
    @Binds
    abstract fun providesCharactersRepository(repo: CharactersRepositoryImpl): CharactersRepository
}
