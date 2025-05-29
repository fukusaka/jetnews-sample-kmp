package com.example.jetnews.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily

@Composable
internal actual fun defaultMontserrat(): FontFamily = if (LocalInspectionMode.current) {
    // TODO: 暫定的に Preview 時にはデフォルトフォントを使う
    FontFamily.Default
} else {
    montserrat()
}

internal actual val defaultPlatformTextStyle: PlatformTextStyle
    get() = PlatformTextStyle(includeFontPadding = false)
