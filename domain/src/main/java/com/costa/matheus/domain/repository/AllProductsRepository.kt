package com.costa.matheus.domain.repository

import com.costa.matheus.domain.entities.ProductEntity
import kotlinx.coroutines.Deferred


interface AllProductsRepository {
    suspend fun getProducts(): Deferred<List<ProductEntity>>
}