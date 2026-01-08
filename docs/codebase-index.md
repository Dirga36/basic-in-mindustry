# Codebase Index — Basic In Mindustry

This index provides a quick map of the core library (Java) files and key configuration for the Mindustry mod in this workspace.

## Source Files

- Mod Entry: [src/example/ExampleJavaMod.java](src/example/ExampleJavaMod.java) — main mod class and event hooks.
- Content: [src/example/content/ExampleBlocks.java](src/example/content/ExampleBlocks.java) — block registration.
- Content: [src/example/content/ExampleUnits.java](src/example/content/ExampleUnits.java) — unit definitions and load patterns.
- Content: [src/example/content/ExampleSounds.java](src/example/content/ExampleSounds.java) — sound assets references.
- Unit Type Base: [src/example/type/unit/ExampleUnitType.java](src/example/type/unit/ExampleUnitType.java) — custom unit type implementation.

## Build & Config

- Gradle Build: [build.gradle](build.gradle) — dependencies, Java & Mindustry settings.
- Gradle Properties: [gradle.properties](gradle.properties) — project name/version.
- Mod Descriptor: [mod.hjson](mod.hjson) — mod ID, name, and `main` entry class.
- Readme: [README.md](README.md) — project overview.

## Assets (Reference)

- Bundles: [assets/bundles/bundle.properties](assets/bundles/bundle.properties) — localized strings.
- Sprites (blocks): [assets/sprites/blocks](assets/sprites/blocks) — block textures.
- Sprites (units): [assets/sprites/units](assets/sprites/units) — unit textures.
- Sounds: [assets/sounds](assets/sounds) — sound files.

## Build Outputs (Generated)

- Jars: [build/libs](build/libs) — desktop/android/deploy jars after Gradle tasks.

---

Tips:
- Source layout follows static fields + `load()` initializers for content.
- Texture atlas keys should be prefixed with the mod ID from `mod.hjson`.