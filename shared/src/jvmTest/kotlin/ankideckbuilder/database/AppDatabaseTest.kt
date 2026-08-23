package ankideckbuilder.database

import ankideckbuilder.database.project.ProjectEntity
import ankideckbuilder.domain.models.project.Project
import java.nio.file.Files
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Instant

class AppDatabaseTest {
    @Test
    fun storesAndObservesProjects() = runBlocking {
        val directory = Files.createTempDirectory("anki-deck-builder-test-")
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
            directory.toFile().deleteRecursively()
        }
    }
}
