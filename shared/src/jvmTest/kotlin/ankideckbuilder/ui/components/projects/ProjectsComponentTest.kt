package ankideckbuilder.ui.components.projects

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import javax.swing.SwingUtilities
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProjectsComponentTest {
    @Test
    fun exposesInitialStateAndDisposesStoreWithComponent() {
        SwingUtilities.invokeAndWait {
            val lifecycle = LifecycleRegistry()
            lifecycle.resume()
            val factory = RecordingStoreFactory()
            try {
                val componentContext = DefaultComponentContext(lifecycle)
                val component =
                    DefaultProjectsComponent(
                        componentContext = componentContext,
                        storeFactory = factory
                    )

                assertEquals(UiState.NoProjects, factory.store.state)
                assertEquals(UiState.NoProjects, component.uiState.value)
                assertFalse(factory.store.isDisposed)
            } finally {
                lifecycle.destroy()
            }
            assertTrue(factory.store.isDisposed)
        }
    }
}

private class RecordingStoreFactory : StoreFactory {
    lateinit var store: Store<*, *, *>

    override fun <Intent : Any, Action : Any, Message : Any, State : Any, Label : Any> create(
        name: String?,
        autoInit: Boolean,
        initialState: State,
        bootstrapper: Bootstrapper<Action>?,
        executorFactory: () -> Executor<Intent, Action, State, Message, Label>,
        reducer: Reducer<State, Message>,
    ): Store<Intent, State, Label> = DefaultStoreFactory().create(
        name = name,
        autoInit = autoInit,
        initialState = initialState,
        bootstrapper = bootstrapper,
        executorFactory = executorFactory,
        reducer = reducer,
    ).also { store = it }
}
