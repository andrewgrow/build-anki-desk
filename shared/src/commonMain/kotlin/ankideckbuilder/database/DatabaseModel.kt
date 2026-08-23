package ankideckbuilder.database

import domain.models.project.DomainModel

interface DatabaseModel<out Domain: DomainModel> {
    fun toDomainModel(): Domain
}

interface DatabaseModelFactory<Domain: DomainModel, out T: DatabaseModel<Domain>> {
    fun fromDomainModel(domainModel: Domain): T
}