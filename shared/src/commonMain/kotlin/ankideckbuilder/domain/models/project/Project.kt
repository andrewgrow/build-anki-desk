package ankideckbuilder.domain.models.project

import domain.models.project.DomainModel
import kotlin.time.Instant

data class Project(
    val id: Long = 0,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
): DomainModel
