plugins {
    id("convention.android.library")
    id("convention.kmp")
    id("convention.kmp.android")
}

android.namespace = "com.example.jetnews.core.testing"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:data"))
        }
    }
}
