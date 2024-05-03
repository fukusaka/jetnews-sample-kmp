import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
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

    val exportedDependencies = listOf<String>(
    )

    val frameworkName = "shared"
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
        }
    }
}
