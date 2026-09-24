package ankideckbuilder.ui.components.projects

import ankideckbuilder.ui.components.projects.UiState.NoProjects
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

// Intents and one-off labels will be introduced with the first project actions.
internal interface ProjectsStateStore : Store<Nothing, UiState, Nothing>

internal fun createProjectsStateStore(storeFactory: StoreFactory): ProjectsStateStore = object :
    ProjectsStateStore,
    Store<Nothing, UiState, Nothing> by storeFactory.create(
        name = "ProjectsStateStore",
        initialState = NoProjects,
        executorFactory = {
            object : CoroutineExecutor<Nothing, Nothing, UiState, Nothing, Nothing>() {}
        },
    ) { /* */ }
