package com.costa.matheus.domain.entities

data class ProductEntity (
    val name: String,
    val description: String,
    val image: String,
    val price: String,
    val promotional_price: String?
): Product()