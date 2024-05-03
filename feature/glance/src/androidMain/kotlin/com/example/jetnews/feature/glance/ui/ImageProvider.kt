package com.example.jetnews.feature.glance.ui

import android.graphics.BitmapFactory
import androidx.glance.ImageProvider
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.getSystemResourceEnvironment

// FIXME: 画像キャッシュしていないため、処理コストが高い。
//  Compose Multiplatform の Common Resources API が改善されたら、修正する
@OptIn(ExperimentalResourceApi::class)
internal fun ImageProvider(image: DrawableResource): ImageProvider {
    val byteArray = runBlocking { getDrawableResourceBytes(getSystemResourceEnvironment(), image) }
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return ImageProvider(bitmap)
}
