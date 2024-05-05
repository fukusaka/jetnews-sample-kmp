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
        }
    }
}
