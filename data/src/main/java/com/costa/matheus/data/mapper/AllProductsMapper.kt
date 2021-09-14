package com.costa.matheus.data.mapper

import com.costa.matheus.data.model.ProductModel
import com.costa.matheus.data.model.ProductsPageResponse
import com.costa.matheus.domain.entities.Product
import com.costa.matheus.domain.entities.ProductEntity
import com.costa.matheus.domain.entities.ProductPageEntity
import com.costa.matheus.domain.entities.SpecialProductEntity

class AllProductsMapper {

    fun map(productPageResponse: ProductsPageResponse): List<Product> {
        val mappedList = mutableListOf<Product>()

        val specialProducts = productPageResponse.specialProducts.map{ mapSpecialProduct(it) }
        val allProducts = productPageResponse.allProducts.map{ mapProduct(it) }

        mappedList.addAll(specialProducts)
        mappedList.addAll(allProducts)

        return mappedList
    }

    private fun mapProduct(productModel: ProductModel): ProductEntity {
        return ProductEntity(
            name = productModel.name,
            description = productModel.description,
            image = productModel.image,
            price = productModel.price,
            promotional_price = productModel.promotional_price
        )
    }

    private fun mapSpecialProduct(productModel: ProductModel): SpecialProductEntity {
        return SpecialProductEntity(
            name = productModel.name,
            description = productModel.description,
            image = productModel.image,
            price = productModel.price,
            promotional_price = productModel.promotional_price
        )
    }

}