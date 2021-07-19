package io.github.teamcheeze.plum.api.modules.signInput

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

interface SignInputModule {

    fun open(player: Player)
    fun register(plugin: JavaPlugin)
}