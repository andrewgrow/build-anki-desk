plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.roborazzi)
    alias(libs.plugins.room)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.decompose)
            implementation(libs.decompose.extensionsCompose)
            implementation(libs.kotlinx.coroutinesCore)
            api(libs.kotlinx.datetime)
            implementation(libs.klogging)
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmTest.dependencies {
            implementation(compose.desktop.uiTestJUnit4)
            implementation(libs.roborazzi.composeDesktop)
        }
    }
}

dependencies {
    add("kspJvm", libs.room.compiler)
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

roborazzi {
    outputDir.set(file("src/jvmTest/screenshots"))
    compare {
        outputDir.set(layout.buildDirectory.dir("outputs/roborazzi-comparison"))
    }
}
