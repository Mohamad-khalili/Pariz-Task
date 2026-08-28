package com.pariz.task.di

import com.google.gson.GsonBuilder
import com.pariz.task.core.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().setLenient().create())

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val timeout : Long = 30
        val dispatcher = Dispatcher()
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .build()
    }


    @Provides
    @Singleton
    fun provideMoviesRetrofit(
        okHttpClient: OkHttpClient,
        covertFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(covertFactory)
        .build()
}