plugins {
    id("convention.android.library")
    id("convention.kmp")
    id("convention.kmp.android")
    id("convention.kmp.ios")
    id("convention.kmp.compose")
    id("convention.kmp.compose.resources")
}

android.namespace = "com.example.jetnews.composeApp"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
            implementation(project(":core:data"))
            implementation(project(":core:ui"))
            implementation(project(":core:designsystem"))

            implementation(project(":feature:article"))
            implementation(project(":feature:interests"))
            implementation(project(":feature:home"))

            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.chrisbanes.kmp.compose.materialWindow)

            implementation(libs.kmp.lifecycle.runtime.compose)
            implementation(libs.kmp.lifecycle.viewmodel.compose)
            implementation(libs.kmp.navigation.compose)
        }
        androidUnitTest.dependencies {
            implementation(project(":core:testing"))
            implementation(libs.androidx.compose.ui.test.junit4)
            implementation(libs.robolectric)
        }
    }
}

android {
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}
