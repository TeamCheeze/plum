package io.github.teamcheeze.plum.api

import org.bukkit.plugin.java.JavaPlugin

class PluginLoader {
    companion object{
        @JvmStatic
        lateinit var plugin: JavaPlugin

        @JvmStatic
        fun checkPluginLoaded(): Boolean{
            return this::plugin.isInitialized
        }
    }
}