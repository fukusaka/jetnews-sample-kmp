package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpIosConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            kotlin {
                iosX64()
                iosArm64()
                iosSimulatorArm64()
            }
        }
    }
}