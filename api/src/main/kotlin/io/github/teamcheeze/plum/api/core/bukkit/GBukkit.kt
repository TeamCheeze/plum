package io.github.teamcheeze.plum.api.core.bukkit

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.PluginManager

/**
 * The Bukkit wrapper class that connects extra features in the library
 * @author dolphin2410
 */
class GBukkit {
    companion object {
        @JvmStatic
        val server: Server
            get() = Bukkit.getServer()
        /**
         * Get the online players with kotlin field accessor
         */
        @JvmStatic
        val onlinePlayers: Collection<Player>
            get() = Bukkit.getOnlinePlayers()

        /**
         * Get the server's plugin manager instance with kotlin field accessor
         */
        @JvmStatic
        val pluginManager: PluginManager
            get() = server.pluginManager

        /**
         * The server's implementation version
         */
        @JvmStatic
        val VERSION: String
            get() = Bukkit.getVersion()

        /**
         * The bukkit's implementation version
         */
        @JvmStatic
        val BUKKIT_VERSION: String
            get() = Bukkit.getBukkitVersion()

        /**
         * The main overworld
         */
        @JvmStatic
        val MAIN_WORLD: World
            get() = Bukkit.getWorld("world")!!

        /**
         * The main nether
         */
        @JvmStatic
        val MAIN_NETHER: World
            get() = Bukkit.getWorld("world_nether")!!

        /**
         * The main end
         */
        @JvmStatic
        val MAIN_END: World
            get() = Bukkit.getWorld("world_the_end")!!
    }
}