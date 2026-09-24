package ankideckbuilder.ui.compose.application

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.v2.runComposeUiTest
import ankideckbuilder.ui.components.application.TestRootComponent
import kotlin.test.Test
import kotlin.test.assertEquals
import androidx.compose.ui.test.performClick
import ankideckbuilder.ui.components.projects.TestProjectsComponent

class RootContentTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun displaysProjectsAndDeliversAddProjectClick() = runComposeUiTest {
        var clicks = 0
        val projects = TestProjectsComponent(onAddProjectClick = { clicks++ })
        val root = TestRootComponent(projects)
        setContent { RootContent(root) }
        onNodeWithText("You have not any projects yet").assertIsDisplayed()
        onNodeWithText("Add Project").assertIsDisplayed()
        onNodeWithText("Add Project").performClick()
        runOnIdle { assertEquals(1, clicks) }
    }

}
