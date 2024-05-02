package convention

import org.gradle.jvm.toolchain.JavaLanguageVersion

internal object Constants {
    val conventionJavaCompilerArgs = listOf(
        "-Xlint:unchecked",
        "-Xlint:deprecation",
    )

    val conventionJavaLanguageVersion = JavaLanguageVersion.of(11)
}
