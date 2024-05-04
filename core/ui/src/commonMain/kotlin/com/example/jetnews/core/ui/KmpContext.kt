package com.example.jetnews.core.ui

import androidx.compose.runtime.ProvidableCompositionLocal

expect abstract class KmpContext

expect val LocalKmpContext: ProvidableCompositionLocal<KmpContext>