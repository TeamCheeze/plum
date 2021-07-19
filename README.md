# Plum
A spigot library, for minecraft servers.

### Other Languages
[Korean](./README_KR.md)

### Notice
I am currently finding people to work together. People who are interested may contact me at [dolphin2410@outlook.kr](dolphin241@outlook.kr)

### Introduction
Plum is an open-source spigot plugin development library. If anyone on the internet is interested in making spigot plugin developer's code simpler, you can freely contribute, fork or copy the code I wrote.

### Core features
- ***GameProfileWrapper***: This contains most of the `mojang-authlib`'s GameProfile's feature. The `unwrap()` function can be used to get the GameProfile instance.
- ***EventRegistry***: It was nearly impossible for java plugin developers to dynamically listen to new events on runtime without creating a separate class that implements listener. However, this comes up with a solution. You can pass the event as a generic, and the registerer automatically listens to that event.
- ***CommandRegistry***: If you used CommandExecutor, it would have been cumbersome to manually add the command information to `plugin.yml`. However, Brigadier and NMS would be harder because it is version-dependent, and this reduces the amount of code you have to write.
- ***InventoryManager***: Custom GUIs are one of the most useful things in plugin development. Why not use inventory guis like a desktop gui? This allows you to do the similar things.
- ***ModuleTemplate***: Sometimes, it is very hard to use NMS, but this allows you to access multiple versions easily

### For people who loves open source
Help me by contributing on this project!

### Notice
- I am currently doing my best on making this api java-compatible. Contribute to help me!
```kotlin
repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    mavenCentral()
}
dependencies {
    compileOnly("io.github.dolphin2410:plum-api:0.0.1")
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    implementation("io.github.dolphin2410:jaw:[the latest version]")
    implementation(kotlin("stdlib"))
}
```

### developing environment
Plum uses JDK 16 with Kotlin version 1.5.20 and depends on Kolleague.