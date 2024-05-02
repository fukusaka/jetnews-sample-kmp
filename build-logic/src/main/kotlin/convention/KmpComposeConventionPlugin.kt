package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KmpComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val compose = extensions.getByName("compose") as org.jetbrains.compose.ComposeExtension
            kotlin {
                with(sourceSets) {
                    getByName("commonMain").dependencies {
                        implementation(compose.dependencies.runtime)
                    }
                }
            }
        }
    }
}