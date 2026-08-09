package ankideckbuilder.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import ankideckbuilder.application.RootComponent
import ankideckbuilder.shared.Platform
import io.github.takahirom.roborazzi.captureRoboImage
import kotlin.test.Test

class RootContentScreenshotTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun rootContentStatesMatchReferences() = runComposeUiTest {
        setContent {
            Box(Modifier.size(width = 800.dp, height = 600.dp)) {
                RootContent(component = TestRootComponent)
            }
        }

        onRoot().captureRoboImage("RootContentScreenshotTest-root-content.png")

        onNodeWithText("Click me!").performClick()
        waitForIdle()

        onRoot().captureRoboImage("RootContentScreenshotTest-root-content-expanded.png")
    }
}

private object TestRootComponent : RootComponent {
    override val platform = object : Platform {
        override val name = "Screenshot Test"
    }
}
