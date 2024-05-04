package com.example.jetnews.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// TODO: compose resources font を使うと Preview が機能しなくなる
@Composable
@Preview
fun FontPreview() {
    JetnewsTheme {
        Column(
            Modifier.background(color = Color.White),
        ) {
            Text("Display Large", style = jetnewsTypography().displayLarge)
            Text("Display Medium", style = jetnewsTypography().displayMedium)
            Text("Display Small", style = jetnewsTypography().displaySmall)
            Text("Headline Large", style = jetnewsTypography().headlineLarge)
            Text("Headline Medium", style = jetnewsTypography().headlineMedium)
            Text("Headline Small", style = jetnewsTypography().headlineSmall)
            Text("Title Large", style = jetnewsTypography().titleLarge)
            Text("Title Medium", style = jetnewsTypography().titleMedium)
            Text("Title Small", style = jetnewsTypography().titleSmall)
            Text("Label Large", style = jetnewsTypography().labelLarge)
            Text("Label Medium", style = jetnewsTypography().labelMedium)
            Text("Label Small", style = jetnewsTypography().labelSmall)
            Text("Body Large", style = jetnewsTypography().bodyLarge)
            Text("Body Medium", style = jetnewsTypography().bodyMedium)
            Text("Body Small", style = jetnewsTypography().bodySmall)
        }
    }
}
