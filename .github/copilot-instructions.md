# Copilot instructions — Basic In Mindustry

This file gives focused, actionable guidance for AI coding agents working on this Mindustry Java mod.

- **Project type:** Mindustry Java mod using Gradle. See [build.gradle](build.gradle).
- **Entry point:** main class declared in [mod.hjson](mod.hjson) `main` field — verify it matches the actual class in `src/` (example: [src/example/ExampleJavaMod.java](src/example/ExampleJavaMod.java)).

- **Build tasks:**
  - Desktop jar: run `gradlew jar` (Windows: `gradlew.bat jar`). Output: `build/libs/${project.name}Desktop.jar`.
  - Android jar: `gradlew jarAndroid` (requires `ANDROID_HOME` / Android SDK and `d8` in PATH).
  - Combined deploy jar: `gradlew deploy` produces `build/libs/${project.name}.jar`.

- **Java/compatibility notes:**
  - `build.gradle` uses `sourceCompatibility = JavaVersion.VERSION_17` but compiles with `--release 8` for bytecode compatibility; use JDK 17+ to build.
  - External dependencies and Mindustry API are pulled from custom Maven repos in `build.gradle` (mindustry version controlled by `mindustryVersion`).

- **Project layout & conventions:**
  - Java source root: `src/`. Content classes live under `src/example/content/` (see [ExampleUnitTypes.java](src/example/content/ExampleUnitTypes.java)).
  - Assets live under `assets/` and are referenced by sprite keys in code; sprite names are expected to be prefixed with the mod name/ID declared in `mod.hjson` (e.g. `basic-in-mindustry-frog`).
  - Content registration pattern: static fields + a `load()` initializer method. Example: `public static ExampleUnitType cax;` and `cax = new ExampleUnitType("cax") {{ ... }};` in `ExampleUnitTypes.load()`.
  - Code frequently uses double-brace initializers and nested anonymous classes (e.g., `new Weapon("...") {{ ... }}` and `new BasicBulletType(...) {{ ... }}`). Preserve this style when adding similar content.

- **Practical examples to reference:**
  - Unit definitions: [src/example/content/ExampleUnitTypes.java](src/example/content/ExampleUnitTypes.java) — shows `parts`, `weapons`, bullets, and sprite keys like `missile-large`.
  - Mod entry: [src/example/ExampleJavaMod.java](src/example/ExampleJavaMod.java) — shows event hooks and atlas lookup example for sprites.

- **When adding content:**
  - Add Java classes under `src/example/content/` with static fields and a `load()` method.
  - Put textures in `assets/sprites/...` and name atlas entries using the mod `name` from `mod.hjson` as prefix.
  - Update `mod.hjson` if you rename the mod ID or change the `main` class — the `main` field must match the fully-qualified mod class.

- **Common troubleshooting:**
  - If `gradlew jar` fails: run `gradlew.bat --stacktrace jar` and ensure `JAVA_HOME` points to JDK 17+.
  - For `jarAndroid`, ensure `ANDROID_HOME`/`ANDROID_SDK_ROOT` is set and `d8` is available in PATH.
  - If asset sprite keys are missing at runtime, confirm file exists under `assets/sprites/` and atlas key matches `${modname}-${sprite}`.

- **Editing style & safety:**
  - Follow existing patterns (double-brace inits, anonymous inner classes) to keep code consistent.
  - Avoid changing global build variables (e.g., `mindustryVersion`) without coordinating version compatibility testing.

If anything here is unclear or you want specific examples added (e.g., how to add a new Unit, Weapon, or sprite atlas entry), tell me which area to expand and I will update this file.
