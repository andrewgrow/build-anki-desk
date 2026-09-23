# Anki Deck Builder

A local desktop application for creating, reviewing, and exporting custom decks as Anki-compatible `.apkg` files. It is built with Kotlin/JVM and Compose Multiplatform Desktop.

The application is intended to manage the complete deck-building workflow:

1. Create or import deck content.
2. Manage notes, generated cards, and their fields.
3. Add and organize images, audio, and other media.
4. Review, edit, validate, and approve content.
5. Export an approved deck as an Anki-compatible `.apkg` package.
6. Import the generated package into a separately installed Anki application.

An Anki note contains the editable fields, while one or more cards can be generated from that note using card templates. The application will preserve this distinction so it can support custom note types and multiple card templates.

## Intended use and Anki compatibility

The application is an independent deck-authoring tool. Its purpose is limited to preparing content and generating `.apkg` files that can be imported into Anki by the user. It does not bundle, modify, launch, or distribute the Anki application, and it does not use Anki source code as an application dependency.

Anki Deck Builder is not affiliated with or endorsed by the Anki project. The name "Anki" is used only to describe compatibility with the package format and the intended destination application. Anki is available separately from [the official Anki website](https://apps.ankiweb.net/).

## Architecture

The Kotlin application owns the desktop UI, application logic, and local project storage. Its handlers/services will cover:

- projects and decks;
- notes, cards, fields, and templates;
- images, audio, and other media;
- importing and batch operations;
- review, validation, and approval;
- export manifest generation;
- Anki package export.

## Technologies

The application is local and single-user, with no backend server or web frontend. Project data and media are stored locally.

- Kotlin/JVM and Compose Multiplatform Desktop for the application and UI.
- [Decompose](https://arkivanov.github.io/Decompose/) for navigation and lifecycle management.
- [Room](https://developer.android.com/kotlin/multiplatform/room) with bundled SQLite for project data.
- [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) for date and time handling.
- Klogging for logging.
- [Roborazzi](https://github.com/takahirom/roborazzi) for screenshot testing.

Anki-compatible `.apkg` export is planned using Python and the `genanki` library.

## Initial milestone

The first milestone is a minimal end-to-end pipeline for one note/card:

```text
content and media -> local review -> approval -> valid Anki package
```

After that, the application can grow to support importing, batch processing, regeneration controls, caching, custom templates, and release management.

## Project structure

This is a Kotlin Multiplatform project targeting Desktop (JVM).

- `desktopApp` contains the desktop application entry point and packaging configuration.
- `shared/src/commonMain` contains shared UI and application code.
- `shared/src/jvmMain` contains JVM-specific implementations.

## Running the application

Use the run configurations provided by the run widget in your IDE's toolbar, or use one of these commands:

- Hot reload: `./gradlew :desktopApp:hotRun --auto`
- Standard run: `./gradlew :desktopApp:run`

To build a standalone application:

```shell
./gradlew :desktopApp:createDistributable
```

The generated application is placed in `desktopApp/build/compose/binaries/main/app/`.

On macOS, launch it with:

```shell
open desktopApp/build/compose/binaries/main/app/org.example.project.app
```

## Running tests

Use the run button in your IDE's editor gutter, or run all test suites at once from the project root:

```shell
./gradlew allTests
```

This currently runs the shared JVM unit and database integration tests together with Roborazzi screenshot verification. To run only the shared desktop tests, use:

```shell
./gradlew :shared:jvmTest
```

Gradle skips tests when nothing has changed since the last run. To force a full re-run (for example, to verify a clean state before pushing, or when test results look stale or flaky), clean the test outputs and disable the build cache:

```shell
./gradlew cleanJvmTest allTests --no-build-cache
```

Desktop screenshot tests use [Roborazzi](https://github.com/takahirom/roborazzi). Ordinary `jvmTest` and IDE test runs verify screenshots by default. To update reference images after an intentional UI change:

```shell
./gradlew :shared:recordRoborazziJvm
```

Review the updated images before committing them. To verify screenshots explicitly:

```shell
./gradlew :shared:verifyRoborazziJvm
```

Reference images are stored in `shared/src/jvmTest/screenshots/`. Roborazzi's Compose Desktop support is experimental, and screenshots can vary across operating systems, fonts, and graphics environments. Record and verify reference images in the same environment.

## Running opencode

Open a terminal in the project root and run:

```shell
opencode
```

## License

Anki Deck Builder is available under the [MIT License](LICENSE.md). See [Third-Party Notices](THIRD_PARTY_NOTICES.md) for dependency licenses and the Anki compatibility notice.
