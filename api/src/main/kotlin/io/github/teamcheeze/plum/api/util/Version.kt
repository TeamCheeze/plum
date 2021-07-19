package io.github.teamcheeze.plum.api.util

import org.bukkit.Bukkit

class Version {
    companion object{
        @JvmStatic
        val BUKKIT_VERSION: String = Bukkit.getServer()::class.javaObjectType.`package`.name.split(".")[3]
        @JvmStatic
        val DEFAULT_NMS_CLASS: String = "io.github.teamcheeze.plum.api.${BUKKIT_VERSION}"
    }
}