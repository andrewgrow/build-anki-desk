package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.application.DefaultRootComponent
import org.example.project.application.RootComponent
import org.example.project.compose.RootContent

// default JVM entry point
fun main() {
    runApplication(DefaultRootComponent())
}

// Starts the shared application root and hosts its Compose content.
fun runApplication(rootComponent: RootComponent) {
    rootComponent.start()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject",
        ) {
            RootContent(rootComponent)
        }
    }
}
