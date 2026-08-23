package ankideckbuilder

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.swing.Swing
import ankideckbuilder.ui.components.application.DefaultRootComponent
import ankideckbuilder.ui.components.application.RootComponent
import ankideckbuilder.ui.compose.application.RootContent
import javax.swing.SwingUtilities

// default JVM entry point
fun main() {
    val lifecycle = LifecycleRegistry()
    val rootComponent = runOnUiThread {
        DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
        )
    }

    runApplication(rootComponent, lifecycle)
}

// Starts the shared application root and hosts its Compose content.
fun runApplication(
    rootComponent: RootComponent,
    lifecycle: LifecycleRegistry,
) = application {
    val windowState = rememberWindowState()

    LifecycleController(lifecycle, windowState)

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "The Anki Deck Builder",
    ) {
        RootContent(rootComponent)
    }
}

private fun <T> runOnUiThread(block: () -> T): T =
    if (SwingUtilities.isEventDispatchThread()) {
        block()
    } else {
        runBlocking(Dispatchers.Swing) { block() }
    }
