plugins {
    id("convention.feature.kmp.compose")
}

android.namespace = "com.example.jetnews.feature.interests"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
    }
}
