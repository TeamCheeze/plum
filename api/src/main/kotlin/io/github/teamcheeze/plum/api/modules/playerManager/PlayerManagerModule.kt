package io.github.teamcheeze.plum.api.modules.playerManager

import com.mojang.authlib.GameProfile
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.net.URI
import java.util.*

interface PlayerManagerModule {
    fun <T>getUniqueId(name: String, runAfter: (UUID)->T): Boolean
    fun <T> generateTexture(uri: URI, runAfter: (Pair<String, String>) -> T): Boolean
    fun <T>getTexture(name: String, runAfter: (Pair<String, String>) -> T)
    fun createProfile(texture: Pair<String, String>): GameProfile
    fun <T>playerHead(name: String, runAfter: (ItemStack) -> T)
    fun <T>playerHead(uri: URI, runAfter: (ItemStack) -> T)
    fun reloadPlayer(player: Player)
    fun modifyTexture(player: Player, uri: URI)
    fun modifyTexture(player: Player, playerName: String)
    fun modifyName(player: Player, name: String)
    fun createTexture(player: Player): Pair<String, String>
}