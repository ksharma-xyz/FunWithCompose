package xyz.ksharma.scrolltotop.data

import kotlinx.collections.immutable.ImmutableMap
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingCart(
    val shoppingCart: ImmutableMap<String, String>
)

@Serializable
data class ShoppingCartItem(
    val name: String,
    val quantity: String
)