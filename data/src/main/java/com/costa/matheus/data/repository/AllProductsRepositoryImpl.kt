package com.costa.matheus.data.repository

import com.costa.matheus.data.datasource.BurgerDataSource
import com.costa.matheus.data.mapper.AllProductsMapper
import com.costa.matheus.data.mapper.DayOfferMapper
import com.costa.matheus.domain.entities.DayOfferEntity
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.repository.AllProductsRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllProductsRepositoryImpl @Inject constructor(
    private val dataSource: BurgerDataSource): AllProductsRepository {

    override suspend fun getProducts() = withContext(Dispatchers.IO) {
        async {
            delay(5000)
            AllProductsMapper().map(dataSource.getAllProducts())
        }
    }

    override suspend fun getDayOffer() = withContext(Dispatchers.IO) {
        async {
            delay(5000)
            DayOfferMapper().map(dataSource.getDayOffer())
        }
    }
}