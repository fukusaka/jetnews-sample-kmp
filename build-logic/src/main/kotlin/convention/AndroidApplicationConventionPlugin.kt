package convention

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                configureAndroid()

                packaging.resources {
                    // Multiple dependency bring these files in. Exclude them to enable
                    // our test APK to build (has no effect on our AARs)
                    excludes += "/META-INF/AL2.0"
                    excludes += "/META-INF/LGPL2.1"
                }
            }
        }
    }
}
