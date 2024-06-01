package xyz.ksharma.scrolltotop.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.coroutines.launch
import xyz.ksharma.scrolltotop.ui.components.ListItem

@Composable
fun ShoppingCartScreen(modifier: Modifier = Modifier, shoppingCart: ImmutableMap<String, String>) {
    val listState = rememberLazyListState()

    // Show the button if the first visible list item is past the first item.
    // We use a remembered derived state to minimize unnecessary compositions.
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val coroutineScope = rememberCoroutineScope()

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var selectedItem: String? by rememberSaveable { mutableStateOf(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
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
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            )
        }

        item {
            AnimatedVisibility(visible = shoppingCart.isEmpty()) {
                Text(
                    text = "No items in list",
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }

        items(shoppingCart.keys.toList()) { key ->
            val item: String = shoppingCart.getValue(key)
            ListItem(productName = key, quantity = item, onClick = { productName ->
                showDialog = true
                selectedItem = productName
            })
        }

        item {
            Spacer(modifier = Modifier.padding(32.dp))
        }
    }

    AnimatedVisibility(
        visible = showButton,
        enter = slideInVertically(
            animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
            initialOffsetY = { it }),
        exit = slideOutVertically(
            animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
            targetOffsetY = { it }
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(24.dp),
            contentAlignment = Alignment.BottomEnd,
        ) {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiary,
                onClick = { coroutineScope.launch { listState.animateScrollToItem(0) } },
                content = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to Top"
                    )
                })
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = { },
            title = {
                Text(text = "Clicked $selectedItem")
            },
            text = {
                Text(text = "Imagine this is more information about the item.")
            }, dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Close")
                }
            })
    }
}
