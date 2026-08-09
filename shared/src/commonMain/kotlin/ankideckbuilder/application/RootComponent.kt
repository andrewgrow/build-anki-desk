package ankideckbuilder.application

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import io.klogging.config.ANSI_INFO
import io.klogging.config.loggingConfiguration
import io.klogging.noCoLogger
import ankideckbuilder.shared.Platform
import ankideckbuilder.shared.getPlatform

interface RootComponent {
    val platform: Platform
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val logger by lazy { noCoLogger("App") }

    init {
        loggingConfiguration { ANSI_INFO() }
        lifecycle.doOnCreate {
            logger.info("Application started")
        }
    }

    override val platform = getPlatform()
}
