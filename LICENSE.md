# License

## Project license

Copyright (c) 2026 The Anki Deck Builder contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Anki compatibility notice

This software is independently developed for the limited purpose of creating
Anki-compatible `.apkg` files. Users may import those files into a separately
obtained installation of Anki.

This software does not bundle, modify, launch, or distribute the Anki
application, and it does not use Anki source code as an application dependency.
Anki is therefore not listed below as a bundled third-party component. The
Anki application and its source code remain subject to their own license terms,
which are available in the [official Anki repository](https://github.com/ankitects/anki/blob/main/LICENSE).

Anki Deck Builder is not affiliated with or endorsed by the Anki project. The
name "Anki" is used only to describe compatibility with the package format and
the intended destination application. No Anki logo or other Anki project asset
is included by this notice.

## Third-party software

This project uses third-party software that remains subject to its own license
terms. The following table records the primary libraries and build tools
currently declared by the project.

| Project | Version used | License | License reference |
| --- | --- | --- | --- |
| Kotlin, Kotlin Gradle plugins, and Kotlin Test | 2.4.10 | Apache License 2.0 | [Kotlin license](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt) |
| Compose Multiplatform | 1.11.1 | Apache License 2.0 | [Compose Multiplatform license](https://github.com/JetBrains/compose-multiplatform/blob/master/LICENSE.txt) |
| Compose Material 3 | 1.11.0-alpha07 | Apache License 2.0 | [AndroidX license](https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt) |
| AndroidX Lifecycle for Compose | 2.11.0-beta01 | Apache License 2.0 | [AndroidX license](https://github.com/androidx/androidx/blob/androidx-main/LICENSE.txt) |
| kotlinx.coroutines | 1.11.0 | Apache License 2.0 | [kotlinx.coroutines license](https://github.com/Kotlin/kotlinx.coroutines/blob/master/LICENSE.txt) |
| Klogging | 0.11.8 | Apache License 2.0 | [Klogging license](https://github.com/klogging/klogging/blob/main/LICENSE.txt) |
| JUnit 4 | 4.13.2 | Eclipse Public License 1.0 | [JUnit 4 license](https://github.com/junit-team/junit4/blob/main/LICENSE-junit.txt) |
| Gradle Wrapper and Gradle build tool | 9.1.0 | Apache License 2.0 | [Gradle license](https://github.com/gradle/gradle/blob/master/LICENSE) |
| Foojay Toolchains Plugin | 1.0.0 | Apache License 2.0 | [Foojay Toolchains license](https://github.com/gradle/foojay-toolchains/blob/main/LICENSE) |

The license references above are provided for convenience. The license and
notice files distributed with each dependency are authoritative. Dependencies
may also bring transitive software with additional notices or license terms.
Before distributing an application package, the resolved dependency graph
should be reviewed and all notices required by the packaged software should be
included with the release.

This file must be updated whenever a third-party library or build tool is added,
removed, or upgraded. Planned dependencies are not listed until they are added
to the project.
