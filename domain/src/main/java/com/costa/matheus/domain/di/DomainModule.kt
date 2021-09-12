package com.costa.matheus.domain.di

import com.costa.matheus.domain.repository.AllProductsRepository
import com.costa.matheus.domain.usecases.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetAllProductsUseCase(repository: AllProductsRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(repository)
    }

}