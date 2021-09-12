package com.costa.matheus.data.di

import com.costa.matheus.data.repository.AllProductsRepositoryImpl
import com.costa.matheus.domain.repository.AllProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    //@Binds
    //abstract fun providesAllProductsRepositoryImpl(allProductsRepositoryImpl: AllProductsRepositoryImpl): AllProductsRepository

    @Binds
    fun AllProductsRepositoryImpl.bindsUseCase(): AllProductsRepository
}