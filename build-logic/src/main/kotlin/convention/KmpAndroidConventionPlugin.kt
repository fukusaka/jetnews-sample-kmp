package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

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
                androidTarget()

                with(sourceSets) {
                    getByName("androidMain").dependencies {
                        implementation(libs.library("androidx-core-ktx"))
                    }
                }
            }
        }
    }
}
