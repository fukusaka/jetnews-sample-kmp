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

package com.example.jetnews.feature.article

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import jetnews.feature.article.generated.resources.Res
import jetnews.feature.article.generated.resources.bookmark
import jetnews.feature.article.generated.resources.cd_add_to_favorites
import jetnews.feature.article.generated.resources.cd_share
import jetnews.feature.article.generated.resources.cd_text_settings
import jetnews.feature.article.generated.resources.ic_text_settings
import jetnews.feature.article.generated.resources.unbookmark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavoriteButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Filled.ThumbUpOffAlt,
            contentDescription = stringResource(Res.string.cd_add_to_favorites),
        )
    }
}

@Composable
fun BookmarkButton(isBookmarked: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val clickLabel = stringResource(
        if (isBookmarked) Res.string.unbookmark else Res.string.bookmark,
    )
    IconToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            // Use a custom click label that accessibility services can communicate to the user.
            // We only want to override the label, not the actual action, so for the action we pass null.
            this.onClick(label = clickLabel, action = null)
        },
    ) {
        Icon(
            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = null, // handled by click label of parent
        )
    }
}

@Composable
fun ShareButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = stringResource(Res.string.cd_share),
        )
    }
}

@Composable
fun TextSettingsButton(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            painter = painterResource(Res.drawable.ic_text_settings),
            contentDescription = stringResource(Res.string.cd_text_settings),
        )
    }
}
