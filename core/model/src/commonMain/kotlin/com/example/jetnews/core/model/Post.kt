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

package com.example.jetnews.core.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource

data class Post(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val publication: Publication? = null,
    val metadata: Metadata,
    val paragraphs: ImmutableList<Paragraph> = persistentListOf(),
    val imageId: DrawableResource,
    val imageThumbId: DrawableResource,
)

data class Metadata(val author: PostAuthor, val date: String, val readTimeMinutes: Int)

data class PostAuthor(val name: String, val url: String? = null)

data class Publication(val name: String, val logoUrl: String)

data class Paragraph(val type: ParagraphType, val text: String, val markups: ImmutableList<Markup> = persistentListOf())

data class Markup(val type: MarkupType, val start: Int, val end: Int, val href: String? = null)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}
