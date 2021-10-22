package com.costa.matheus.domain.entities

import java.io.Serializable

open class Product (
    val name: String,
    val description: String,
    val image: String,
    val price: String,
    val promotional_price: String?
): Serializable