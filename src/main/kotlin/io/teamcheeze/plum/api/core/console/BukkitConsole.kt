package io.teamcheeze.plum.api.core.console

import org.bukkit.Bukkit

/**
 * The console feature of the server
 * @author dolphin2410
 */
class BukkitConsole {
    companion object {
        /**
         * Writes a given message to the console
         * @param msg The message to print
         */
        @JvmStatic
        fun write(msg: String) {
            Bukkit.getServer().consoleSender.sendMessage(msg)
        }

        /**
         * Writes a give raw message to the console
         * @param msg The message to print
         */
        @JvmStatic
        fun writeRaw(msg: String) {
            Bukkit.getServer().consoleSender.sendRawMessage(msg)
        }
    }
}