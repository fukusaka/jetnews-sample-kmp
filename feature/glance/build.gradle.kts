plugins {
    id("convention.feature.kmp.compose")
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.example.jetnews.feature.glance"
    buildFeatures {
        buildConfig = true
    }
    dependencies {
        implementation(libs.androidx.glance)
        implementation(libs.androidx.glance.appwidget)
        implementation(libs.androidx.glance.material3)
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}
