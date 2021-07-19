package io.github.teamcheeze.plum.api.core.alert

import io.github.teamcheeze.plum.api.core.debug.BukkitDebug
import io.github.dolphin2410.jaw.util.async.Async
import org.bukkit.Bukkit
import org.bukkit.ChatColor

/**
 * Alerting system for bukkit. Calls asynchronously
 * @author dolphin2410
 */
class BukkitAlert {
    companion object{
        /**
         * warn indicates the color Yellow
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun warn(msg: String){
            Async.execute {
                Bukkit.broadcastMessage("${ChatColor.YELLOW}$msg")
            }
        }

        /**
         * A debug purpose method. Called by [BukkitDebug]. The color is blue
         * @param msg The message to be sent to all players on the server
         */
        @JvmStatic
        fun debug(msg: String) {
            Async.execute {
                Bukkit.broadcastMessage("${ChatColor.BLUE}$msg")
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