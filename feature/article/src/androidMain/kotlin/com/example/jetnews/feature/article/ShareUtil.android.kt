package com.example.jetnews.feature.article

import android.content.Intent
import com.example.jetnews.core.model.Post
import com.example.jetnews.core.ui.KmpContext
import jetnews.feature.article.generated.resources.Res
import jetnews.feature.article.generated.resources.article_share_post
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString

/**
 * Show a share sheet for a post
 *
 * @param post to share
 * @param context Android context to show the share sheet in
 */
actual fun sharePost(post: Post, context: KmpContext) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.url)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            runBlocking { getString(Res.string.article_share_post) },
        ),
    )
}
