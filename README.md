# Plum
A spigot library, for minecraft servers.

### Other Languages
[Korean](./README_KR.md)

### Notice
I am currently finding people to work together. People who are interested may contact me at [teamcheeze@outlook.kr](https://teamcheeze@outlook.kr)

### Introduction
Plum is an open-source spigot-plugin-development library. You can freely contribute to, fork or copy the code I wrote.

## Examples
### GameProfileWrapper
```kotlin
GameProfileWrapper(UUID.randomUUID(), "Name", "value" to "signature")
```
### EventRegistry
```kotlin
EventRegistry.register<BlockBreakEvent> { e ->
    if (e.player.name == "Dream") {
        e.isCancelled = true
    }
}
```

### CommandRegistry
```kotlin
CommandRegistry.register("plum") {
    option("heal") {
        input { playerName ->
            executes {
                Bukkit.getPlayer(playerName)?.let {
                    it.health = 20.0
                }
            }
        }
    }
    option("feed") {
        input { playerName ->
            executes {
                Bukkit.getPlayer(playerName)?.let {
                    it.foodLevel = 20
                }
            }
        }
    }
}
```

### For people who love open source
Help me by contributing on this project!

### Notice
- I am currently doing my best on making this api java-compatible. Contribute to help me!
```kotlin
repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    mavenCentral()
}
dependencies {
    compileOnly("io.github.teamcheeze:plum:0.0.5")
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    implementation("io.github.dolphin2410:jaw:1.0.2")
    implementation(kotlin("stdlib"))
}
```

### developing environment
Plum requires JDK 16 with Kotlin version 1.5.21 and [jaw](https://github.com/TeamCheeze/jaw).
