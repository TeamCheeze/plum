# Plum
스피갓 서버를 위한 라이브러리

### Other Languages
[English](./README.md)

### Introduction
Plum은 오픈소스 스피갓 플러그인 개발 라이브러리입니다. 코드를 자유롭게 사용하셔도 좋습니다.

## 예시 코드
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

### 공지

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

### 요구사항
Plum은 JDK 16과 Kotlin 버전 1.5.21, 그리고 [jaw](https://github.com/TeamCheeze/jaw) 라이브러리가 런타임에 개별적으로 제공 되어야 합니다.