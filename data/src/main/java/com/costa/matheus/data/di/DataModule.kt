package com.costa.matheus.data.di

import com.costa.matheus.data.datasource.BurgerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(Singleton::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideBurgerDataSource(okHttpClient: OkHttpClient): BurgerDataSource {
        return RetrofitUtils.createWebService(okHttpClient, "")
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return RetrofitUtils.createOkHttpClient()
    }
}