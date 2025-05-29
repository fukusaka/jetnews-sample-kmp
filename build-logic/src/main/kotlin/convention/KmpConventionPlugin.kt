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
                            "-Xexpect-actual-classes",
                        ),
                    )
                }
                with(sourceSets) {
                    getByName("commonMain").dependencies {
                        implementation(project.dependencies.platform(libs.library("kotlin-bom")))
                        implementation(project.dependencies.platform(libs.library("kotlinx-coroutines-bom")))
                        implementation(libs.library("kotlinx-coroutines-core"))
                        implementation(libs.library("kotlinx-collections-immutable"))
                    }
                    getByName("commonTest").dependencies {
                        implementation(libs.library("kotlinx-coroutines-test"))
                    }
                }
            }
        }
    }
}
