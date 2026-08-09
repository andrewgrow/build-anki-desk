package org.example.project.application

import io.klogging.config.ANSI_INFO
import io.klogging.config.loggingConfiguration
import io.klogging.noCoLogger
import org.example.project.Platform
import org.example.project.getPlatform

interface RootComponent {
    fun start()
    val platform: Platform
}

class DefaultRootComponent : RootComponent {
    private val logger by lazy { noCoLogger("App") }

    override fun start() {
        loggingConfiguration { ANSI_INFO() }
        logger.info("Application started")
    }

    override val platform = getPlatform()
}
