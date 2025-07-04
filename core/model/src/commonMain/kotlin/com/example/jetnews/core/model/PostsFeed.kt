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

package com.example.jetnews.core.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

/**
 * A container of [Post]s, partitioned into different categories.
 */
data class PostsFeed(val highlightedPost: Post, val recommendedPosts: ImmutableList<Post>, val popularPosts: ImmutableList<Post>, val recentPosts: ImmutableList<Post>) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: ImmutableList<Post> =
        (listOf(highlightedPost) + recommendedPosts + popularPosts + recentPosts).toImmutableList()
}
