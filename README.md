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

[Decompose](https://arkivanov.github.io/Decompose/) provides lifecycle-aware application components and keeps component logic separate from Compose content. The initial integration contains only the root component and desktop lifecycle; navigation and screen-specific child components will be introduced with the first real workflows.

Anki package generation is delegated internally to a small Python worker using the `genanki` library. The worker consumes a manifest and local media prepared by the Kotlin application and produces the final `.apkg` file. Python is an implementation detail and does not provide a separate backend or user interface.

The application is local and single-user. Structured project metadata is stored locally in a [Room](https://developer.android.com/kotlin/multiplatform/room) database backed by bundled SQLite. Room entities and DAOs live in shared code, while the JVM-specific database builder selects the correct application-data directory for macOS, Windows, or Linux. Timestamps use `Instant` with [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) and are persisted as Unix epoch milliseconds. Generated images and audio remain regular files; the database will store their paths and metadata rather than binary media.

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

Desktop screenshot tests use [Roborazzi](https://github.com/takahirom/roborazzi). The root test captures both the initial UI and its expanded state after clicking the button. Ordinary `jvmTest` and IDE test runs verify screenshots by default. Record approved reference images only after reviewing an intentional UI change, or run verification explicitly:

```shell
./gradlew :shared:recordRoborazziJvm
./gradlew :shared:verifyRoborazziJvm
```

Reference images are stored in `shared/src/jvmTest/screenshots/`. Roborazzi's Compose Desktop support is experimental, and screenshots can vary across operating systems, fonts, and graphics environments. Until a canonical CI environment is configured, reference images should be recorded and verified on the same environment.

## License

Anki Deck Builder is available under the [MIT License](LICENSE.md). See [Third-Party Notices](THIRD_PARTY_NOTICES.md) for dependency licenses and the Anki compatibility notice.
