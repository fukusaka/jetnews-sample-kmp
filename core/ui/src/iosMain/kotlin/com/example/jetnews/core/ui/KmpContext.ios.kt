package com.example.jetnews.core.ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

actual abstract class KmpContext

private val defaultKmpContext = object : KmpContext() {}

actual val LocalKmpContext: ProvidableCompositionLocal<KmpContext> = staticCompositionLocalOf { defaultKmpContext }
