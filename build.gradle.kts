plugins {
    alias(libs.plugins.kover)
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.room) apply false
}

dependencies {
    kover(project(":shared"))
    kover(project(":desktopApp"))
}

kover {
    reports {
        total {
            verify {
                rule {
                    minBound(75)
                }
            }
        }
        filters {
            excludes {
                packages("ankideckbuilder.shared.generated.resources")
                classes(
                    "ankideckbuilder.database.AppDatabase_Impl*",
                    "ankideckbuilder.database.AppDatabaseConstructor*",
                    "ankideckbuilder.database.project.ProjectDao_Impl*",
                )
            }
        }
    }
}
