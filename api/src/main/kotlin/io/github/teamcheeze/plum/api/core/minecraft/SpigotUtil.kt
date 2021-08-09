package io.github.teamcheeze.plum.api.core.minecraft

import org.bukkit.Bukkit

class SpigotUtil {
    companion object {
        @JvmStatic
        val console = Bukkit.getServer().consoleSender
    }
}