package xyz.ksharma.scrolltotop.data

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingCart(
    val shoppingCart: Map<String, String>
)

@Serializable
data class ShoppingCartItem(
    val name: String,
    val quantity: String
)