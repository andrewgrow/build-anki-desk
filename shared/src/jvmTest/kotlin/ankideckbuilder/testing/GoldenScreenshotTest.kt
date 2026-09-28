package ankideckbuilder.testing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.rules.TestName

/**
 * JUnit 4 Compose screenshot helpers with a shared theme and screen size.
 * Golden paths are `package/Class/method.png`, without `ankideckbuilder.` and `ScreenshotTest`.
 * Each test method has one golden path; repeated captures reuse it.
 */
abstract class GoldenScreenshotTest {
    /** Requested content size in dp, subject to the test host's layout constraints. */
    data class Screen(val width: Dp, val height: Dp)

    companion object {
        internal val defaultScreen = Screen(800.dp, 600.dp)
    }

    @get:Rule
    val testName = TestName()

    /** Calls [setGoldenContent] then [captureGolden] for tests without intermediate actions. */
    @OptIn(ExperimentalTestApi::class)
    protected fun setAndCaptureGolden(
        composeUiTest: ComposeUiTest,
        screen: Screen = defaultScreen,
        content: @Composable () -> Unit,
    ) {
        setGoldenContent(composeUiTest, screen, content)
        captureGolden(composeUiTest)
    }

    /**
     * Sets themed content at [screen] size without capturing. Call once per Compose test scope,
     * then perform any actions before [captureGolden].
     */
    @OptIn(ExperimentalTestApi::class)
    protected fun setGoldenContent(
        composeUiTest: ComposeUiTest,
        screen: Screen = defaultScreen,
        content: @Composable () -> Unit,
    ) {
        composeUiTest.setContent {
            MaterialTheme {
                Box(Modifier.size(width = screen.width, height = screen.height)) {
                    content()
                }
            }
        }
    }

    /**
     * Waits for Compose idleness and captures existing content during a running JUnit test.
     * For asynchronous loading, wait for the expected UI state before calling this method.
     */
    @OptIn(ExperimentalTestApi::class)
    protected fun captureGolden(composeUiTest: ComposeUiTest) {
        composeUiTest.waitForIdle()
        val componentPath = this@GoldenScreenshotTest.javaClass.name
            .removePrefix("ankideckbuilder.")
            .removeSuffix("ScreenshotTest")
            .replace('.', '/')
        val methodName = checkNotNull(testName.methodName) {
            "captureGolden() must be called from a running JUnit test."
        }
        composeUiTest.onRoot().captureRoboImage("$componentPath/$methodName.png")
    }
}
