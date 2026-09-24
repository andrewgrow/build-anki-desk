package ankideckbuilder.ui.components.projects

import ankideckbuilder.testing.ComponentTest
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProjectsComponentTest : ComponentTest() {
    @Test
    fun exposesInitialStateAndDisposesStoreWithComponent() {
        runOnUiThread {
            lifecycle.resume()
            val factory = RecordingStoreFactory()
            val component = DefaultProjectsComponent(
                componentContext = DefaultComponentContext(lifecycle),
                storeFactory = factory,
            )

            assertEquals(UiState.NoProjects, factory.store.state)
            assertEquals(UiState.NoProjects, component.uiState.value)
            assertFalse(factory.store.isDisposed)

            lifecycle.destroy()
            assertTrue(factory.store.isDisposed)
        }
    }
}
