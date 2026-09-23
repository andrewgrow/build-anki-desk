package ankideckbuilder.ui.components.projects

import com.arkivanov.decompose.ComponentContext

interface ProjectsComponent {
    fun onAddProject()
}

class DefaultProjectsComponent(
    componentContext: ComponentContext,
) : ProjectsComponent, ComponentContext by componentContext {
    override fun onAddProject() {
        // Project creation will be added in the next step.
    }
}
