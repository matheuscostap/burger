package com.costa.matheus.domain.entities

data class OrderItem (
    val product: Product,
    val quantity: Int
) {

    val totalPrice: Double
        get() = product.price
            .removePrefix("R$")
            .trim()
            .replace(",", ".")
            .toDouble() * quantity

    val formattedPrice: String
        get() = "R$ ${String.format("%.2f", totalPrice).replace(".", ",")}"
}