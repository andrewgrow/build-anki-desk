package ankideckbuilder.ui.components.projects

import com.arkivanov.decompose.value.MutableValue

class TestProjectsComponent(
    initialState: UiState = UiState.NoProjects,
    private val onAddProjectClick: () -> Unit = {},
) : ProjectsComponent {
    override val uiState = MutableValue(initialState)

    override fun onAddProject() = onAddProjectClick()
}
