package xyz.ksharma.scrolltotop.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import xyz.ksharma.scrolltotop.ui.components.ListItem
import xyz.ksharma.scrolltotop.ui.components.ScrollToTopButton

@Composable
fun ShoppingCartScreen(shoppingCart: ImmutableMap<String, String>) {
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer),
        contentPadding = PaddingValues(vertical = 32.dp),
        state = listState,
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

        item {
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                ScrollToTopButton(
                    onClick = {
                      //  coroutineScope { listState.animateScrollToItem(0) }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                )
            }
        }
    }
}
