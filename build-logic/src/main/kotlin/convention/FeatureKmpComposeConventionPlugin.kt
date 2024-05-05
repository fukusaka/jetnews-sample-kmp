package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class FeatureKmpComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.kmp")
                apply("convention.kmp.android")
                apply("convention.kmp.compose")
                apply("convention.kmp.compose.resources")
            }

            val compose = extensions.getByName("compose") as org.jetbrains.compose.ComposeExtension
            kotlin {
                with(sourceSets) {
                    getByName("commonMain").dependencies {
                        implementation(project(":core:model"))
                        implementation(project(":core:data"))
                        implementation(project(":core:ui"))
                        implementation(project(":core:designsystem"))

                        implementation(compose.dependencies.foundation)
                        implementation(compose.dependencies.material)
                        implementation(compose.dependencies.material3)
                        implementation(compose.dependencies.materialIconsExtended)
                    }
                    getByName("androidMain").dependencies {
                        implementation(compose.dependencies.preview)

                        val composeBom = project.dependencies.platform(libs.library("androidx-compose-bom"))
                        implementation(composeBom)
                    }
                }
            }
        }
    }
}
