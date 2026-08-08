This is a Kotlin Multiplatform project targeting Desktop (JVM).

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`

- Build a standalone application:
  - `./gradlew :desktopApp:createDistributable` (artifact is building here `desktopApp/build/compose/binaries/main/app/`)
  - Then launch the generated app: (Terminal) `open desktopApp/build/compose/binaries/main/app/org.example.project.app`

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Desktop tests: `./gradlew :shared:jvmTest`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…