package ankideckbuilder.ui.compose.projects

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import ankideckbuilder.ui.components.projects.TestProjectsComponent
import kotlin.test.Test

class ProjectsContentTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun displaysAndClosesEditorFromSlot() = runComposeUiTest {
        val component = TestProjectsComponent()
        setContent { ProjectsContent(component) }

        onNodeWithText("Create project").assertDoesNotExist()
        onNodeWithText("Add Project").performClick()
        onNodeWithText("Create project").assertIsDisplayed()
        onNodeWithText("Close").performClick()
        onNodeWithText("Create project").assertDoesNotExist()
        onNodeWithText("Add Project").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun closesInitiallyOpenEditor() = runComposeUiTest {
        val component = TestProjectsComponent(editorInitiallyOpen = true)
        setContent { ProjectsContent(component) }

        onNodeWithText("Create project").assertIsDisplayed()
        onNodeWithText("Close").performClick()
        onNodeWithText("Create project").assertDoesNotExist()
        onNodeWithText("Add Project").performClick()
        onNodeWithText("Create project").assertIsDisplayed()
    }
}
