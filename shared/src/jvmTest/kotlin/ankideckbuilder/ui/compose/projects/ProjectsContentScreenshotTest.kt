package ankideckbuilder.ui.compose.projects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import ankideckbuilder.testing.GoldenScreenshotTest
import ankideckbuilder.ui.components.projects.TestProjectsComponent
import kotlin.test.Test

class ProjectsContentScreenshotTest : GoldenScreenshotTest() {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun emptyProjectsStateMatchesReference() = runComposeUiTest {
        val component = TestProjectsComponent()
        setContent {
            MaterialTheme {
                Box(Modifier.size(width = 800.dp, height = 600.dp)) {
                    ProjectsContent(component)
                }
            }
        }

        captureGolden()
    }
}
