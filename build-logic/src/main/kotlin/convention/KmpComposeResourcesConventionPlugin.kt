package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.resources.ResourcesExtension

@Suppress("unused")
class KmpComposeResourcesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val compose = extensions.getByName("compose") as org.jetbrains.compose.ComposeExtension
            kotlin {
                with(sourceSets) {
                    getByName("commonMain").dependencies {
                        implementation(compose.dependencies.components.resources)
                    }
                }
            }
            compose.resources { 
                publicResClass = true
            }
        }
    }
}
