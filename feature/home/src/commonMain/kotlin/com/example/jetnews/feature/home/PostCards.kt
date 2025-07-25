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

package com.example.jetnews.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetnews.core.model.Post
import com.example.jetnews.feature.home.utils.BookmarkButton
import jetnews.feature.home.generated.resources.Res
import jetnews.feature.home.generated.resources.agree
import jetnews.feature.home.generated.resources.bookmark
import jetnews.feature.home.generated.resources.cd_more_actions
import jetnews.feature.home.generated.resources.fewer_stories
import jetnews.feature.home.generated.resources.fewer_stories_content
import jetnews.feature.home.generated.resources.home_post_based_on_history
import jetnews.feature.home.generated.resources.home_post_min_read
import jetnews.feature.home.generated.resources.unbookmark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthorAndReadTime(post: Post, modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(
            text = stringResource(
                resource = Res.string.home_post_min_read,
                formatArgs = arrayOf(
                    post.metadata.author.name,
                    post.metadata.readTimeMinutes,
                ),
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun PostImage(post: Post, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(post.imageThumbId),
        contentDescription = null, // decorative
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small),
    )
}

@Composable
fun PostTitle(post: Post) {
    Text(
        text = post.title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun PostCardSimple(post: Post, navigateToArticle: (String) -> Unit, isFavorite: Boolean, onToggleFavorite: () -> Unit) {
    val bookmarkAction = stringResource(if (isFavorite) Res.string.unbookmark else Res.string.bookmark)
    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToArticle(post.id) })
            .semantics {
                // By defining a custom action, we tell accessibility services that this whole
                // composable has an action attached to it. The accessibility service can choose
                // how to best communicate this action to the user.
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = bookmarkAction,
                        action = {
                            onToggleFavorite()
                            true
                        },
                    ),
                )
            },
    ) {
        PostImage(post, Modifier.padding(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp),
        ) {
            PostTitle(post)
            AuthorAndReadTime(post)
        }
        BookmarkButton(
            isBookmarked = isFavorite,
            onClick = onToggleFavorite,
            // Remove button semantics so action can be handled at row level
            modifier = Modifier
                .clearAndSetSemantics {}
                .padding(vertical = 2.dp, horizontal = 6.dp),
        )
    }
}

@Composable
fun PostCardHistory(post: Post, navigateToArticle: (String) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }

    Row(
        Modifier
            .clickable(onClick = { navigateToArticle(post.id) }),
    ) {
        PostImage(
            post = post,
            modifier = Modifier.padding(16.dp),
        )
        Column(
            Modifier
                .weight(1f)
                .padding(vertical = 12.dp),
        ) {
            Text(
                text = stringResource(Res.string.home_post_based_on_history),
                style = MaterialTheme.typography.labelMedium,
            )
            PostTitle(post = post)
            AuthorAndReadTime(
                post = post,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
        IconButton(onClick = { openDialog = true }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(Res.string.cd_more_actions),
            )
        }
    }
    if (openDialog) {
        AlertDialog(
            modifier = Modifier.padding(20.dp),
            onDismissRequest = { openDialog = false },
            title = {
                Text(
                    text = stringResource(Res.string.fewer_stories),
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.fewer_stories_content),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(Res.string.agree),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { openDialog = false },
                )
            },
        )
    }
}
