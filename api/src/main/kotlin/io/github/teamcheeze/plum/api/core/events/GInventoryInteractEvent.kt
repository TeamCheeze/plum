package io.github.teamcheeze.plum.api.core.events

import io.github.teamcheeze.plum.api.core.inventory.GInventory
import io.github.teamcheeze.plum.api.core.inventory.GInventoryInteractType
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

data class GInventoryInteractEvent(
    val type: GInventoryInteractType,
    val inventory: GInventory,
    val position: GInventory.Slot?
): Event(false), Cancellable {
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
    private var isCancelled = false
    override fun isCancelled(): Boolean {
        return isCancelled
    }
    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }
}