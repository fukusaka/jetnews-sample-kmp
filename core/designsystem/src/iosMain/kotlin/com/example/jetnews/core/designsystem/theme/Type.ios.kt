package com.example.jetnews.core.designsystem.theme

import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle

internal actual val defaultPlatformTextStyle: PlatformTextStyle
    get() = PlatformTextStyle(spanStyle = PlatformSpanStyle.Default, paragraphStyle = PlatformParagraphStyle.Default)
