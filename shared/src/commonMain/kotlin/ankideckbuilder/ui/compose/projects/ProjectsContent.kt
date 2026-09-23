package ankideckbuilder.ui.compose.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ankideckbuilder.ui.components.projects.ProjectsComponent
import ankideckbuilder.ui.theme.Spacing

@Composable
fun ProjectsContent(component: ProjectsComponent) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = Spacing.medium,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        Text("You have not any projects yet")
        Button(onClick = component::onAddProject) {
            Text("Add Project")
        }
    }
}
