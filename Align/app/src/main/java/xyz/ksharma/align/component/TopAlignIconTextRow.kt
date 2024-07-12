package xyz.ksharma.align.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopAlignIconTextRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp), verticalAlignment = Alignment.Top
    ) {
        Icon(imageVector = Icons.Default.Info, contentDescription = "info")
        Text(
            "Hello, this is a text. It can span across multiple lines.",
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview
@Preview(fontScale = 2f)
@Composable
private fun TopAlignIconTextRowPreview() {
    Surface {
        TopAlignIconTextRow()
    }
}
