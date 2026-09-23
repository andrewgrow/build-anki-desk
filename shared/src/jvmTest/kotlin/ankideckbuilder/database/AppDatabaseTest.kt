package ankideckbuilder.database

import ankideckbuilder.database.project.ProjectEntity
import ankideckbuilder.domain.models.project.Project
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Instant

class AppDatabaseTest {
    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun storesAndObservesProjects() = runBlocking {
        val directory = temporaryFolder.root.toPath()
        val database = buildDatabase(createDatabaseBuilder(directory.resolve("test.db")))

        try {
            val timestamp = Instant.fromEpochMilliseconds(1_000L)
            val project = Project(
                name = "Marine English for Sailors",
                createdAt = timestamp,
                updatedAt = timestamp,
            )

            val projectId = database.projectDao().insert(ProjectEntity.fromDomainModel(project))
            val storedProject = project.copy(id = projectId)

            assertEquals(1L, projectId)
            assertEquals(storedProject, database.projectDao().findById(projectId)?.toDomainModel())
            assertEquals(
                listOf(storedProject),
                database.projectDao().observeAll().first().map(ProjectEntity::toDomainModel),
            )
        } finally {
            database.close()
        }
    }
}
