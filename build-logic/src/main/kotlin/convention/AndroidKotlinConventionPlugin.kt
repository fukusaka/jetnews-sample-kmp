package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidKotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            android {
                kotlinOptions {
                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-progressive",
                        "-Xexpect-actual-classes",
                        "-Xjavac-arguments='${Constants.conventionJavaCompilerArgs.joinToString(" ")}'",
                    )
                    jvmTarget = Constants.conventionJavaVersion.toString()
                }
            }

            with(dependencies) {
                add("implementation", project.dependencies.platform(libs.library("kotlin-bom")))
                add("implementation", project.dependencies.platform(libs.library("kotlinx-coroutines-bom")))
                add("implementation", libs.library("kotlinx-coroutines-core"))
                add("implementation", libs.library("kotlinx-collections-immutable"))
                add("testImplementation", libs.library("kotlinx-coroutines-test"))
            }
        }
    }
}
