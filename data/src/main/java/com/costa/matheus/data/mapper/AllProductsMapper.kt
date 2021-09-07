package com.costa.matheus.data.mapper

import com.costa.matheus.data.model.ProductModel
import com.costa.matheus.domain.entities.ProductEntity

class AllProductsMapper {

    fun map(modelList: List<ProductModel>): List<ProductEntity> {
        return modelList.map {
            ProductEntity(
                name = it.name,
                description = it.description,
                image = it.image,
                price = it.price,
                promotional_price = it.promotional_price
            )
        }
    }

}