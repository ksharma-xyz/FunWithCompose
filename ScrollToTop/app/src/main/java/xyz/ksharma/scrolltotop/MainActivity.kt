package xyz.ksharma.scrolltotop

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.coroutines.coroutineScope
import xyz.ksharma.scrolltotop.ui.theme.ScrollToTopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollToTopTheme {
                val context: Context = LocalContext.current
                var shoppingCart: ImmutableMap<String, String> by remember { mutableStateMapOf("" to "") }
                LaunchedEffect(key1 = Unit) {
                    coroutineScope {
                        shoppingCart = Repository.fetchGroceryList(context).shoppingCart
                    }
                }
                ShoppingCartScreen(shoppingCart)
            }
        }
    }

    @Composable
    private fun ShoppingCartScreen(shoppingCart: ImmutableMap<String, String>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            contentPadding = PaddingValues(vertical = 32.dp),
        ) {
            item {
                Text(
                    text = "Shopping Cart",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                )
            }

            item {
                AnimatedVisibility(visible = shoppingCart.isEmpty()) {
                    Text(
                        text = "No items in list",
                        modifier = Modifier.padding(24.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }

            items(shoppingCart.keys.toList()) { key ->
                val item: String = shoppingCart.getValue(key)
                ListItem(productName = key, quantity = item)
            }
        }
    }
}

@Composable
fun ListItem(productName: String, quantity: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = productName,
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Text(
            text = quantity,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@PreviewLightDark
@Composable
fun ListItemPreview() {
    ScrollToTopTheme {
        ListItem("Orange", "1KG")
    }
}
