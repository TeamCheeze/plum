repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    compileOnly("com.mojang:authlib:1.5.21")
}