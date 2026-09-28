package ankideckbuilder.ui.compose.projects.editor

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ankideckbuilder.ui.components.projects.editor.EditorComponent

@Composable
fun EditorContent(component: EditorComponent) {
    AlertDialog(
        onDismissRequest = component::onClose,
        title = { Text("Create project") },
        text = { Text("Project details will be added here.") },
        confirmButton = {
            TextButton(onClick = component::onClose) {
                Text("Close")
            }
        },
    )
}
