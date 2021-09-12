package com.costa.matheus.data.di

import com.costa.matheus.data.datasource.BurgerDataSource
import com.costa.matheus.data.repository.AllProductsRepositoryImpl
import com.costa.matheus.domain.repository.AllProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideBurgerDataSource(): BurgerDataSource {
        val okHttpClient = RetrofitUtils.createOkHttpClient()
        return RetrofitUtils.createWebService(okHttpClient, "https://raw.githubusercontent.com/")
    }
}