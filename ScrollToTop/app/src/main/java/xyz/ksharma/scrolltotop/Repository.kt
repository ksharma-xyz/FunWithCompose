package xyz.ksharma.scrolltotop

import android.content.Context
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import xyz.ksharma.scrolltotop.data.ShoppingCart
import java.io.InputStream

object Repository {

    suspend fun fetchGroceryList(context: Context): ShoppingCart = withContext(Dispatchers.IO) {
        delay(2000)
        val inputStream: InputStream = context.assets.open("shopping_cart.json")
        val jsonString = inputStream.toJsonString()
        return@withContext jsonStringToDataClass<ShoppingCart>(jsonString)
    }

    private inline fun <reified T> jsonStringToDataClass(jsonString: String): T {
        return Json.decodeFromString(jsonString)
    }

    private fun InputStream.toJsonString(): String {
        return bufferedReader().use { it.readText() }
    }
}
