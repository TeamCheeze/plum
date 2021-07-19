package io.github.teamcheeze.plum.api.core.events

import io.github.teamcheeze.plum.api.core.events.manager.SimpleCancellable
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Called when sign input is triggered
 * @param player The player who triggered this event
 * @param msg The message the player wrote
 * @author dolphin2410
 */
data class SignInputEvent(val player: Player, val msg: Array<String>): SimpleCancellable, Event() {
    companion object {
        @JvmStatic
        val HANDLERS = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }
    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignInputEvent

        if (player != other.player) return false
        if (!msg.contentEquals(other.msg)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = player.hashCode()
        result = 31 * result + msg.contentHashCode()
        return result
    }
}