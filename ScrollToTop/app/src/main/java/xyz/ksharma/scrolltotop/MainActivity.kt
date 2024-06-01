package xyz.ksharma.scrolltotop

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.coroutineScope
import xyz.ksharma.scrolltotop.ui.ShoppingCartScreen
import xyz.ksharma.scrolltotop.ui.theme.ScrollToTopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollToTopTheme {
                val context: Context = LocalContext.current
                var shoppingCart by remember { mutableStateOf<Map<String, String>>(emptyMap()) }

                LaunchedEffect(key1 = Unit) {
                    coroutineScope {
                        shoppingCart = Repository.fetchGroceryList(context).shoppingCart
                    }
                }
                ShoppingCartScreen(
                    modifier = Modifier,
                    shoppingCart = shoppingCart.toImmutableMap()
                )
            }
        }
    }
}
