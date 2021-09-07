package com.costa.matheus.domain.di

import com.costa.matheus.data.datasource.BurgerDataSource
import com.costa.matheus.data.repository.AllProductsRepositoryImpl
import com.costa.matheus.domain.repository.AllProductsRepository
import com.costa.matheus.domain.usecases.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn(Singleton::class)
@Module
object DomainModule {

    @Provides
    fun provideGetAllProductsUseCase(repository: AllProductsRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(repository)
    }

    @Provides
    fun providesAllProductsRepository(dataSource: BurgerDataSource): AllProductsRepository {
        return AllProductsRepositoryImpl(dataSource)
    }

}