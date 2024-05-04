package com.example.jetnews.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily

@Composable
internal actual fun defaultMontserrat(): FontFamily = montserrat()

internal actual val defaultPlatformTextStyle: PlatformTextStyle
    get() = PlatformTextStyle(spanStyle = PlatformSpanStyle.Default, paragraphStyle = PlatformParagraphStyle.Default)
