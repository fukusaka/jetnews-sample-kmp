plugins {
    id("convention.kmp")
    id("convention.kmp.android")
    id("convention.kmp.compose")
    id("convention.kmp.compose.resources")
}

android.namespace = "com.example.jetnews.core.ui"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.preview)
        }
    }
}
