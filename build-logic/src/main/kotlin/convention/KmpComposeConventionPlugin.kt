package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

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
                composeCompiler {
                    reportsDestination.set(layout.buildDirectory.dir("reports-compose"))
                    metricsDestination.set(layout.buildDirectory.dir("reports-compose"))
                }

                with(sourceSets) {
                    getByName("commonMain").dependencies {
                        implementation(compose.dependencies.runtime)
                    }
                }
            }

            android {
                with(buildFeatures) {
                    this.compose = true
                }
                dependencies {
                    val composeBom = project.dependencies.platform(libs.library("androidx-compose-bom"))
                    add("implementation", composeBom)
                    add("debugImplementation", libs.library("androidx-compose-ui-tooling"))
                }
            }
        }
    }
}