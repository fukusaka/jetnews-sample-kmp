import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("convention.android.library")
    id("convention.kmp")
    id("convention.kmp.android")
    id("convention.kmp.ios")
    id("convention.kmp.compose")
}

android.namespace = "com.example.jetnews.shared"

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    val exportedDependencies = listOf(
        ":composeApp",
        ":core:model",
        ":core:data",
        ":core:designsystem",
        ":core:ui",
        ":feature:article",
        ":feature:home",
        ":feature:interests",
    )

    val frameworkName = "Shared"
    val xcf = XCFramework(frameworkName)
    targets.filterIsInstance<KotlinNativeTarget>().forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = frameworkName
            isStatic = true
            xcf.add(this)
            exportedDependencies.forEach { dependency -> export(dependency) }
        }
    }

    sourceSets {
        commonMain.dependencies {
            exportedDependencies.forEach { api(project(it)) }
            implementation(libs.kmp.material3.window.sizeClass)
        }
    }
}
