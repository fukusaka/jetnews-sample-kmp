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

package com.example.jetnews.feature.interests

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnews.core.data.Result
import com.example.jetnews.core.data.interests.impl.FakeInterestsRepository
import com.example.jetnews.core.designsystem.theme.JetnewsTheme
import kotlinx.coroutines.runBlocking

@Preview("Interests screen", "Interests")
@Preview("Interests screen (dark)", "Interests", uiMode = UI_MODE_NIGHT_YES)
@Preview("Interests screen (big font)", "Interests", fontScale = 1.5f)
@Composable
fun PreviewInterestsScreenDrawer() {
    JetnewsTheme {
        val tabContent = getFakeTabsContent()
        val (currentSection, updateSection) = rememberSaveable {
            mutableStateOf(tabContent.first().section)
        }

        InterestsScreen(
            tabContent = tabContent,
            currentSection = currentSection,
            isExpandedScreen = false,
            onTabChange = updateSection,
            openDrawer = { },
            snackbarHostState = SnackbarHostState(),
        )
    }
}

@Preview("Interests screen navrail", "Interests", device = Devices.PIXEL_C)
@Preview(
    "Interests screen navrail (dark)",
    "Interests",
    uiMode = UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_C,
)
@Preview(
    "Interests screen navrail (big font)",
    "Interests",
    fontScale = 1.5f,
    device = Devices.PIXEL_C,
)
@Composable
fun PreviewInterestsScreenNavRail() {
    JetnewsTheme {
        val tabContent = getFakeTabsContent()
        val (currentSection, updateSection) = rememberSaveable {
            mutableStateOf(tabContent.first().section)
        }

        InterestsScreen(
            tabContent = tabContent,
            currentSection = currentSection,
            isExpandedScreen = true,
            onTabChange = updateSection,
            openDrawer = { },
            snackbarHostState = SnackbarHostState(),
        )
    }
}

@Preview("Interests screen topics tab", "Topics")
@Preview("Interests screen topics tab (dark)", "Topics", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopicsTab() {
    val topics = runBlocking {
        (FakeInterestsRepository().getTopics() as Result.Success).data
    }
    JetnewsTheme {
        Surface {
            TabWithSections(topics, setOf()) { }
        }
    }
}

@Preview("Interests screen people tab", "People")
@Preview("Interests screen people tab (dark)", "People", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPeopleTab() {
    val people = runBlocking {
        (FakeInterestsRepository().getPeople() as Result.Success).data
    }
    JetnewsTheme {
        Surface {
            TabWithTopics(people, setOf()) { }
        }
    }
}

@Preview("Interests screen publications tab", "Publications")
@Preview("Interests screen publications tab (dark)", "Publications", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPublicationsTab() {
    val publications = runBlocking {
        (FakeInterestsRepository().getPublications() as Result.Success).data
    }
    JetnewsTheme {
        Surface {
            TabWithTopics(publications, setOf()) { }
        }
    }
}

private fun getFakeTabsContent(): List<TabContent> {
    val interestsRepository = FakeInterestsRepository()
    val topicsSection = TabContent(Sections.Topics) {
        TabWithSections(
            runBlocking { (interestsRepository.getTopics() as Result.Success).data },
            emptySet(),
        ) { }
    }
    val peopleSection = TabContent(Sections.People) {
        TabWithTopics(
            runBlocking { (interestsRepository.getPeople() as Result.Success).data },
            emptySet(),
        ) { }
    }
    val publicationSection = TabContent(Sections.Publications) {
        TabWithTopics(
            runBlocking { (interestsRepository.getPublications() as Result.Success).data },
            emptySet(),
        ) { }
    }

    return listOf(topicsSection, peopleSection, publicationSection)
}
