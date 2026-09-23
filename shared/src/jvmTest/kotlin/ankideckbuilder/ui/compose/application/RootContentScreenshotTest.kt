package ankideckbuilder.ui.compose.application

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import ankideckbuilder.ui.components.application.TestRootComponent
import ankideckbuilder.testing.GoldenScreenshotTest
import kotlin.test.Test

class RootContentScreenshotTest : GoldenScreenshotTest() {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun emptyProjectsStateMatchesReference() = runComposeUiTest {
        val component = TestRootComponent()
        setContent {
            Box(Modifier.size(width = 800.dp, height = 600.dp)) {
                RootContent(component = component)
            }
        }

        onNodeWithText("You have not any projects yet").assertIsDisplayed()
        onNodeWithText("Add Project").assertIsDisplayed()
        captureGolden()
    }
}
