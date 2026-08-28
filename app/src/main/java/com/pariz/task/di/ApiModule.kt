package com.pariz.task.di

import com.pariz.task.data.remote.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MoviesApiService =
        retrofit.create(MoviesApiService::class.java)
}