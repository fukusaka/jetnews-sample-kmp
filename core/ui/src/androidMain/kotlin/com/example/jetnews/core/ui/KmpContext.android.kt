package com.example.jetnews.core.ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext

actual typealias KmpContext = android.content.Context

actual val LocalKmpContext: ProvidableCompositionLocal<KmpContext> = LocalContext
