package io.teamcheeze.plum.api.core.inventory.util

import io.github.teamcheeze.plum.api.core.inventory.GInventory
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.inventory.InventoryClickEvent

data class GInventoryClickEvent(val bukkitEvent: InventoryClickEvent, val clickedSlot: Slot, val inventory: GInventory): Event(), Cancellable {
    companion object {
        @JvmStatic
        private val HANDLERS = HandlerList()
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
        bukkitEvent.isCancelled = true
        isCancelled = cancel
    }

}