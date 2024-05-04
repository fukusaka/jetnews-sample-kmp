plugins {
    id("convention.feature.kmp.compose")
}

android.namespace = "com.example.jetnews.feature.home"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:article"))

            implementation(libs.kmp.lifecycle.runtime.compose)
            implementation(libs.kmp.lifecycle.viewmodel.compose)
            implementation(libs.kmp.navigation.compose)
        }
        androidMain.dependencies {
            implementation(libs.accompanist.swiperefresh)
        }
    }
}
