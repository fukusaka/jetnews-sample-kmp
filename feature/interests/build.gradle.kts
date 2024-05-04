plugins {
    id("convention.feature.kmp.compose")
}

android.namespace = "com.example.jetnews.feature.interests"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kmp.lifecycle.runtime.compose)
            implementation(libs.kmp.lifecycle.viewmodel.compose)
        }
    }
}
