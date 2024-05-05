package convention

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion

internal object Constants {
    val conventionJavaCompilerArgs = listOf(
        "-Xlint:unchecked",
        "-Xlint:deprecation",
    )

    private const val CONVENTION_JDK_VERSION = 17
    val conventionJavaVersion: JavaVersion = JavaVersion.toVersion(CONVENTION_JDK_VERSION)
    val conventionJavaLanguageVersion: JavaLanguageVersion = JavaLanguageVersion.of(CONVENTION_JDK_VERSION)
}
