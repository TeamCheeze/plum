package io.github.teamcheeze.plum.api.util.minecraft

import org.bukkit.Bukkit

@Deprecated("This feature is now deprecated. Use the config feature instead")
class Version {
    companion object{
        @JvmStatic
        val BUKKIT_VERSION: String = Bukkit.getServer()::class.javaObjectType.`package`.name.split(".")[3]
        @Deprecated("Use the config feature instead")
        @JvmStatic
        val DEFAULT_NMS_CLASS: String = "io.github.teamcheeze.plum.api.$BUKKIT_VERSION"
    }
}