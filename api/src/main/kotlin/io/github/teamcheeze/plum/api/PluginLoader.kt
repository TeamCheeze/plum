package io.github.teamcheeze.plum.api

import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import org.bukkit.Bukkit
import org.bukkit.plugin.SimplePluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

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