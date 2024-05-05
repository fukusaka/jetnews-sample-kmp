plugins {
    id("convention.feature.kmp.compose")
}

android {
    namespace = "com.example.jetnews.feature.glance"
    dependencies {
        implementation(libs.androidx.glance)
        implementation(libs.androidx.glance.appwidget)
        implementation(libs.androidx.glance.material3)
    }
}
