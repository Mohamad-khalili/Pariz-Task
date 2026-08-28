package com.pariz.task.di

import com.pariz.task.data.remote.MoviesApiService
import com.pariz.task.data.repositoryImpl.MoviesRepositoryImpl
import com.pariz.task.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepo(
        api: MoviesApiService
    ): MoviesRepository = MoviesRepositoryImpl(api)
}