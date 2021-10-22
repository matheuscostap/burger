package com.costa.matheus.domain.entities

class SpecialProductEntity (
    name: String,
    description: String,
    image: String,
    price: String,
    promotional_price: String?
): Product(name,description,image,price,promotional_price)