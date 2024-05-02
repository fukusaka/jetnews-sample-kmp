package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

@OptIn(ExperimentalKotlinGradlePluginApi::class)
@Suppress("unused")
class KmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
            }
            kotlin {
                jvmToolchain {
                    languageVersion.set(Constants.conventionJavaLanguageVersion)
                }
                compilerOptions {
                    freeCompilerArgs.set(
                        freeCompilerArgs.get() + listOf(
                            "-progressive",
                            "-Xjavac-arguments='${Constants.conventionJavaCompilerArgs.joinToString(" ")}'",
                        )
                    )
                }
            }
        }
    }
}
