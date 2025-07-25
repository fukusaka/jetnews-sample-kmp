/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.composeApp.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetnews.composeApp.JetnewsDestinations
import jetnews.composeapp.generated.resources.Res
import jetnews.composeapp.generated.resources.home_title
import jetnews.composeapp.generated.resources.ic_jetnews_logo
import jetnews.composeapp.generated.resources.interests_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppNavRail(currentRoute: String, navigateToHome: () -> Unit, navigateToInterests: () -> Unit, modifier: Modifier = Modifier) {
    NavigationRail(
        header = {
            Icon(
                painterResource(Res.drawable.ic_jetnews_logo),
                null,
                Modifier.padding(vertical = 12.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        modifier = modifier,
    ) {
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == JetnewsDestinations.HOME_ROUTE,
            onClick = navigateToHome,
            icon = { Icon(Icons.Filled.Home, stringResource(Res.string.home_title)) },
            label = { Text(stringResource(Res.string.home_title)) },
            alwaysShowLabel = false,
        )
        NavigationRailItem(
            selected = currentRoute == JetnewsDestinations.INTERESTS_ROUTE,
            onClick = navigateToInterests,
            icon = { Icon(Icons.AutoMirrored.Filled.ListAlt, stringResource(Res.string.interests_title)) },
            label = { Text(stringResource(Res.string.interests_title)) },
            alwaysShowLabel = false,
        )
        Spacer(Modifier.weight(1f))
    }
}
