package convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

@OptIn(ExperimentalKotlinGradlePluginApi::class)
@Suppress("unused")
class KmpAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            android {
                configureAndroid()
            }

            kotlin {
                androidTarget {
                    compilerOptions {
                        freeCompilerArgs.set(
                            freeCompilerArgs.get() + listOf(
                                "-Xjavac-arguments='${Constants.conventionJavaCompilerArgs.joinToString(" ")}'",
                            )
                        )
                    }
                }

                with(sourceSets) {
                    getByName("androidMain").dependencies {
                        implementation(libs.library("androidx-core-ktx"))
                    }
                }
            }
        }
    }
}
