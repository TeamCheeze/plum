package io.github.teamcheeze.plum.api.core.alert

import io.github.dolphin2410.jaw.util.async.Async
import io.github.teamcheeze.plum.api.core.debug.BukkitDebug
import io.github.teamcheeze.plum.api.core.minecraft.SpigotUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * Alerting system for bukkit. Calls asynchronously
 * @author dolphin2410
 */
class BukkitAlert {
    /**
     * Bootstrap Color Code
     */
    enum class BCC(val bukkitCode: ChatColor) {
        WARN(ChatColor.YELLOW),
        DEBUG(ChatColor.BLUE),
        DANGER(ChatColor.RED),
        SUCCESS(ChatColor.GREEN),
        INFO(ChatColor.LIGHT_PURPLE)
    }
    companion object{

        @JvmStatic
        private fun text(code: BCC, msg: String): String {
            return "${code.bukkitCode}$msg"
        }

        /**
         * warn indicates the color Yellow
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun warn(msg: String){
            Async.execute {
                Bukkit.broadcastMessage(text(BCC.WARN, msg))
            }
        }

        @JvmStatic
        fun warnPlayer(player: Player, msg: String) {
            Async.execute {
                player.sendMessage(text(BCC.WARN, msg))
            }
        }

        @JvmStatic
        fun warnLog(msg: String) {
            Async.execute {
                SpigotUtil.console.sendMessage(text(BCC.WARN, msg))
            }
        }

        /**
         * A debug purpose method. Called by [BukkitDebug]. The color is blue
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun debug(msg: String) {
            Async.execute {
                Bukkit.broadcastMessage(text(BCC.DEBUG))
            }
        }

        @JvmStatic
        fun debugPlayer(player: Player, msg: String) {
            Async.execute {
                player.sendMessage(text(BCC.DEBUG, msg))
            }
        }

        /**
         * Danger indicates the color Red
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun danger(msg: String){
            Async.execute {
                Bukkit.broadcastMessage("${ChatColor.RED}$msg")
            }
        }

        /**
         * Success indicates the color Green
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun success(msg: String){
            Async.execute {
                Bukkit.broadcastMessage("${ChatColor.GREEN}$msg")
            }
        }

        /**
         * Success indicates the color SkyBlue, but since there is no SkyBlue option in bukkit, LightPurple is used instead
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun info(msg: String){
            Async.execute {
                Bukkit.broadcastMessage("${ChatColor.LIGHT_PURPLE}$msg")
            }
        }
    }
}