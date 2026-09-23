package ankideckbuilder.testing

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onRoot
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.rules.TestName

abstract class GoldenScreenshotTest {
    @get:Rule
    val testName = TestName()

    protected fun SemanticsNodeInteractionsProvider.captureGolden() {
        val componentPath = this@GoldenScreenshotTest.javaClass.name
            .removePrefix("ankideckbuilder.")
            .removeSuffix("ScreenshotTest")
            .replace('.', '/')
        val methodName = checkNotNull(testName.methodName) {
            "captureGolden() must be called from a running JUnit test."
        }
        onRoot().captureRoboImage("$componentPath/$methodName.png")
    }
}
