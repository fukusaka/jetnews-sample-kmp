plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.secrets)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        androidMain {
            dependencies {
                implementation(project(":core:model"))
                implementation(project(":core:data"))
                implementation(project(":core:ui"))
                implementation(project(":core:designsystem"))

                implementation(project(":feature:article"))

                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)
                implementation(libs.google.android.material)

                val composeBom = project.dependencies.platform(libs.androidx.compose.bom)
                implementation(composeBom)
                implementation(libs.androidx.compose.material.iconsExtended)
                implementation(libs.androidx.compose.material3)
                implementation(libs.androidx.compose.foundation.layout)
                implementation(libs.androidx.compose.ui.tooling.preview)

                implementation(libs.androidx.glance)
                implementation(libs.androidx.glance.appwidget)
                implementation(libs.androidx.glance.material3)
            }
        }
        commonMain.dependencies {
            implementation(compose.runtime)
        }
    }
}

android {
    namespace = "com.example.jetnews.feature.glance"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
    dependencies {
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.androidx.test.espresso.core)
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}