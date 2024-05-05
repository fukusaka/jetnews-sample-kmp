package com.example.jetnews.shared

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController
import com.example.jetnews.composeApp.JetnewsApp
import com.example.jetnews.core.data.AppContainerImpl

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    val appContainer = AppContainerImpl()
    val widthSizeClass = calculateWindowSizeClass().widthSizeClass
    JetnewsApp(appContainer, widthSizeClass)
}
