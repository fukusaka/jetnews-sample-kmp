package com.example.jetnews.composeApp

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.example.jetnews.core.data.Constants.JETNEWS_APP_URI

actual object JetnewsDeepLinks {
    actual val home: List<NavDeepLink> = listOf(
        navDeepLink {
            uriPattern =
                "$JETNEWS_APP_URI/${JetnewsDestinations.HOME_ROUTE}?$POST_ID={$POST_ID}"
        }
    )
}
