package com.example.jetnews.core.ui

import androidx.compose.runtime.Composable

@Composable
expect fun KmpBackHandler(enabled: Boolean = true, onBack: () -> Unit): Unit
