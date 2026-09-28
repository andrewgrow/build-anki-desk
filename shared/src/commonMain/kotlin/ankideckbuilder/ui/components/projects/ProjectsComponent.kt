package ankideckbuilder.ui.components.projects

import ankideckbuilder.ui.store.bindStoreToLifecycle
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory

interface ProjectsComponent {
    val uiState: Value<UiState>

    fun onAddProject()
}

class DefaultProjectsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = DefaultStoreFactory(),
    executorFactory: () -> ProjectsExecutor = ::RealProjectsExecutor,
) : ProjectsComponent,
    ComponentContext by componentContext {
    private val stateStore = createProjectsStore(storeFactory, executorFactory)
        .also { it.accept(ProjectsIntent.LoadProjects) }
    override val uiState: Value<UiState> = bindStoreToLifecycle(stateStore, lifecycle)

    override fun onAddProject() {
        // Project creation will be added in the next step.
    }
}
