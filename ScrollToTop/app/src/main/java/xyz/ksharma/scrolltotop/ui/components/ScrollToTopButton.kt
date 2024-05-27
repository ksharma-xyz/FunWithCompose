package xyz.ksharma.scrolltotop.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { Icon(Icons.Filled.KeyboardArrowUp, "Scroll to Top") }
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        content()
    }
}