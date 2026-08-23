package ankideckbuilder.filesystem

import java.nio.file.Path

private const val APPLICATION_DIRECTORY_NAME = "AnkiDeckBuilder"

fun applicationDataDirectory(): Path {
    val userHome = Path.of(requireNotNull(System.getProperty("user.home")))
    val operatingSystem = System.getProperty("os.name").lowercase()

    return when {
        operatingSystem.contains("mac") ->
            userHome.resolve("Library/Application Support/$APPLICATION_DIRECTORY_NAME")

        operatingSystem.contains("win") ->
            System.getenv("APPDATA")
                ?.takeIf(String::isNotBlank)
                ?.let(Path::of)
                ?.resolve(APPLICATION_DIRECTORY_NAME)
                ?: userHome.resolve("AppData/Roaming/$APPLICATION_DIRECTORY_NAME")

        else ->
            System.getenv("XDG_DATA_HOME")
                ?.takeIf(String::isNotBlank)
                ?.let(Path::of)
                ?.resolve(APPLICATION_DIRECTORY_NAME)
                ?: userHome.resolve(".local/share/$APPLICATION_DIRECTORY_NAME")
    }
}
