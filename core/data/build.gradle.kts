plugins {
    id("convention.kmp")
    id("convention.kmp.android")
    id("convention.kmp.compose")
    id("convention.kmp.compose.resources")
}

android.namespace = "com.example.jetnews.core.data"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
        }
    }
}
