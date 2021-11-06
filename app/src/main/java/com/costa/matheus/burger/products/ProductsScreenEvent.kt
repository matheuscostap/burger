package com.costa.matheus.burger.products

import com.costa.matheus.domain.entities.ProductEntity

abstract class ProductsScreenEvent

data class OnProductClick(
    val product: ProductEntity
): ProductsScreenEvent()
