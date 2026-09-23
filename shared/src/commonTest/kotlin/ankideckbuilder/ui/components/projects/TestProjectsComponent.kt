package ankideckbuilder.ui.components.projects

class TestProjectsComponent(
    private val onAddProjectClick: () -> Unit = {},
) : ProjectsComponent {
    override fun onAddProject() = onAddProjectClick()
}
