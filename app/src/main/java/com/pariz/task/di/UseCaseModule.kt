package com.pariz.task.di

import com.pariz.task.domain.interactor.MoviesInteractor
import com.pariz.task.domain.interactor.MoviesInteractorImpl
import com.pariz.task.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMoviesInteractor(
        moviesRepository: MoviesRepository
    ): MoviesInteractor = MoviesInteractorImpl(moviesRepository)
}