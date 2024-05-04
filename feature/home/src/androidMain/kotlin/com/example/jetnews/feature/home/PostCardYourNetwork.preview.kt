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

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.jetnews.core.data.posts.impl.post1
import com.example.jetnews.core.data.posts.impl.post2
import com.example.jetnews.core.data.posts.impl.post3
import com.example.jetnews.core.data.posts.impl.post4
import com.example.jetnews.core.data.posts.impl.post5
import com.example.jetnews.core.designsystem.theme.JetnewsTheme
import com.example.jetnews.core.model.Post
import com.example.jetnews.core.model.PostAuthor

@Preview("Regular colors")
@Preview("Dark colors", uiMode = UI_MODE_NIGHT_YES)
@Composable
internal fun PreviewPostCardPopular(
    @PreviewParameter(PostPreviewParameterProvider::class, limit = 1) post: Post
) {
    JetnewsTheme {
        Surface {
            PostCardPopular(post, {})
        }
    }
}

@Preview("Regular colors, long text")
@Composable
internal fun PreviewPostCardPopularLongText(
    @PreviewParameter(PostPreviewParameterProvider::class, limit = 1) post: Post
) {
    val loremIpsum =
        """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ullamcorper pharetra massa,
        sed suscipit nunc mollis in. Sed tincidunt orci lacus, vel ullamcorper nibh congue quis.
        Etiam imperdiet facilisis ligula id facilisis. Suspendisse potenti. Cras vehicula neque sed
        nulla auctor scelerisque. Vestibulum at congue risus, vel aliquet eros. In arcu mauris,
        facilisis eget magna quis, rhoncus volutpat mi. Phasellus vel sollicitudin quam, eu
        consectetur dolor. Proin lobortis venenatis sem, in vestibulum est. Duis ac nibh interdum,
        """.trimIndent()
    JetnewsTheme {
        Surface {
            PostCardPopular(
                post.copy(
                    title = "Title$loremIpsum",
                    metadata = post.metadata.copy(
                        author = PostAuthor("Author: $loremIpsum"),
                        readTimeMinutes = Int.MAX_VALUE
                    )
                ),
                {}
            )
        }
    }
}

/**
 * Provides sample [Post] instances for Composable Previews.
 *
 * When creating a Composable Preview using @Preview, you can pass sample data
 * by annotating a parameter with @PreviewParameter:
 *
 * ```
 * @Preview
 * @Composable
 * fun MyPreview(@PreviewParameter(PostPreviewParameterProvider::class, limit = 2) post: Post) {
 *   MyComposable(post)
 * }
 * ```
 *
 * In this simple app we just return the hard-coded posts. When the app
 * would be more complex - e.g. retrieving the posts from a server - this would
 * be the right place to instantiate dummy instances.
 */
internal class PostPreviewParameterProvider : PreviewParameterProvider<Post> {
    override val values = sequenceOf(
        post1, post2, post3, post4, post5
    )
}
