package convention

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.ksp(
    artifact: MinimalExternalModuleDependency,
) {
    add("ksp", artifact)
}

internal fun DependencyHandlerScope.kspTest(
    artifact: MinimalExternalModuleDependency,
) {
    add("kspTest", artifact)
}
