package convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroid() {
    android {
        namespace?.let {
            this.namespace = it
        }
        compileSdkVersion(libs.version("compileSdk").toInt())

        defaultConfig {
            minSdk = libs.version("minSdk").toInt()
            consumerProguardFiles("consumer-rules.pro")
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = Constants.conventionJavaVersion
            targetCompatibility = Constants.conventionJavaVersion
        }

        dependencies {
            add("coreLibraryDesugaring", libs.library("core-jdk-desugaring"))
        }
    }
}
