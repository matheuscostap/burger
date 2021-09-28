package com.costa.matheus.data.repository

import com.costa.matheus.data.datasource.BurgerDataSource
import com.costa.matheus.data.mapper.AllProductsMapper
import com.costa.matheus.data.mapper.DayOfferMapper
import com.costa.matheus.domain.entities.DayOfferEntity
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.repository.AllProductsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllProductsRepositoryImpl @Inject constructor(
    private val dataSource: BurgerDataSource): AllProductsRepository {

    override suspend fun getProducts() = withContext(Dispatchers.IO) {
        async {
            AllProductsMapper().map(dataSource.getAllProducts())
        }
    }

    override suspend fun getDayOffer() = withContext(Dispatchers.IO) {
        async {
            DayOfferMapper().map(dataSource.getDayOffer())
        }
    }
}