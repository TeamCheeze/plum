package io.github.teamcheeze.plum.api.modules.playerManager

import com.mojang.authlib.GameProfile
import io.github.teamcheeze.plum.api.modules.core.Module
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.net.URI
import java.util.*

/**
 * Manages player data. BETA
 * @author dolphin2410
 */
class PlayerManager {
    companion object: PlayerManagerModule {
        //FIX THIS WONT WORK
        private val VERSION_MATCH_MODULE = Module<PlayerManagerModule>("{nms}").getNmsInstance()
        override fun createProfile(texture: Pair<String, String>): GameProfile = VERSION_MATCH_MODULE.createProfile(texture)
        override fun createTexture(player: Player): Pair<String, String> = VERSION_MATCH_MODULE.createTexture(player)
        override fun <T> generateTexture(uri: URI, runAfter: (Pair<String, String>) -> T): Boolean = VERSION_MATCH_MODULE.generateTexture(uri, runAfter)
        override fun <T> getTexture(name: String, runAfter: (Pair<String, String>) -> T) = VERSION_MATCH_MODULE.getTexture(name, runAfter)
        override fun <T> getUniqueId(name: String, runAfter: (UUID) -> T): Boolean = VERSION_MATCH_MODULE.getUniqueId(name, runAfter)
        override fun modifyName(player: Player, name: String) = VERSION_MATCH_MODULE.modifyName(player, name)
        override fun modifyTexture(player: Player, playerName: String) = VERSION_MATCH_MODULE.modifyTexture(player, playerName)
        override fun modifyTexture(player: Player, uri: URI) = VERSION_MATCH_MODULE.modifyTexture(player, uri)
        override fun <T> playerHead(name: String, runAfter: (ItemStack) -> T) = VERSION_MATCH_MODULE.playerHead(name, runAfter)
        override fun <T> playerHead(uri: URI, runAfter: (ItemStack) -> T) = VERSION_MATCH_MODULE.playerHead(uri, runAfter)
        override fun reloadPlayer(player: Player) = VERSION_MATCH_MODULE.reloadPlayer(player)
    }
}