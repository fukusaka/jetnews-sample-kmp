/*
 * Copyright 2020 The Android Open Source Project
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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetnews.composeApp.JetnewsDestinations
import jetnews.composeapp.generated.resources.Res
import jetnews.composeapp.generated.resources.app_name
import jetnews.composeapp.generated.resources.home_title
import jetnews.composeapp.generated.resources.ic_jetnews_logo
import jetnews.composeapp.generated.resources.ic_jetnews_wordmark
import jetnews.composeapp.generated.resources.interests_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppDrawer(currentRoute: String, navigateToHome: () -> Unit, navigateToInterests: () -> Unit, closeDrawer: () -> Unit, modifier: Modifier = Modifier) {
    ModalDrawerSheet(modifier) {
        JetNewsLogo(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp),
        )
        NavigationDrawerItem(
            label = { Text(stringResource(Res.string.home_title)) },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == JetnewsDestinations.HOME_ROUTE,
            onClick = {
                navigateToHome()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
        NavigationDrawerItem(
            label = { Text(stringResource(Res.string.interests_title)) },
            icon = { Icon(Icons.AutoMirrored.Filled.ListAlt, null) },
            selected = currentRoute == JetnewsDestinations.INTERESTS_ROUTE,
            onClick = {
                navigateToInterests()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
    }
}

@Composable
private fun JetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(Res.drawable.ic_jetnews_logo),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painter = painterResource(Res.drawable.ic_jetnews_wordmark),
            contentDescription = stringResource(Res.string.app_name),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
