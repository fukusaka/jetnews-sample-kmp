plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.compose.gradle.plugin)
    implementation(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "convention.android.application"
            implementationClass = "convention.AndroidApplicationConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "convention.android.library"
            implementationClass = "convention.AndroidLibraryConventionPlugin"
        }
        register("AndroidKotlin") {
            id = "convention.android.kotlin"
            implementationClass = "convention.AndroidKotlinConventionPlugin"
        }
        register("KotlinMpp") {
            id = "convention.kmp"
            implementationClass = "convention.KmpConventionPlugin"
        }
        register("KotlinMppAndroid") {
            id = "convention.kmp.android"
            implementationClass = "convention.KmpAndroidConventionPlugin"
        }
        register("KotlinMppIos") {
            id = "convention.kmp.ios"
            implementationClass = "convention.KmpIosConventionPlugin"
        }
        register("KotlinMppCompose") {
            id = "convention.kmp.compose"
            implementationClass = "convention.KmpComposeConventionPlugin"
        }
        register("KotlinMppComposeResources") {
            id = "convention.kmp.compose.resources"
            implementationClass = "convention.KmpComposeResourcesConventionPlugin"
        }
        register("KotlinMppFeatureCompose") {
            id = "convention.feature.kmp.compose"
            implementationClass = "convention.FeatureKmpComposeConventionPlugin"
        }
    }
}
