package ankideckbuilder.ui.components.projects

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory

interface ProjectsComponent {
    val uiState: Value<UiState>

    fun onAddProject()
}

class DefaultProjectsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = DefaultStoreFactory(),
) : ProjectsComponent,
    ComponentContext by componentContext {
    private val stateStore = createProjectsStateStore(storeFactory)
    private val mutableValue = MutableValue(stateStore.state)
    override val uiState: Value<UiState> = mutableValue

    init {
        val subscription = stateStore.states(observer { mutableValue.value = it })
        lifecycle.doOnDestroy {
            subscription.dispose()
            stateStore.dispose()
        }
    }

    override fun onAddProject() {
        // Project creation will be added in the next step.
    }
}
