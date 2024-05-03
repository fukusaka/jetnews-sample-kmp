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
}

gradlePlugin {
    plugins {
        register("KotlinMpp") {
            id = "convention.kmp"
            implementationClass = "convention.KmpConventionPlugin"
        }
        register("KotlinMppAndroid") {
            id = "convention.kmp.android"
            implementationClass = "convention.KmpAndroidConventionPlugin"
        }
        register("KotlinMppCompose") {
            id = "convention.kmp.compose"
            implementationClass = "convention.KmpComposeConventionPlugin"
        }
        register("KotlinMppComposeResources") {
            id = "convention.kmp.compose.resources"
            implementationClass = "convention.KmpComposeResourcesConventionPlugin"
        }
    }
}
