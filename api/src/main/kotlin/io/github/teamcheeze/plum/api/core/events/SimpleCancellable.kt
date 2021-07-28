package io.github.teamcheeze.plum.api.core.events

import org.bukkit.event.Cancellable

/**
 * Reduce the amount of code for simple cancellable events
 * @author dolphin2410
 */
interface SimpleCancellable: Cancellable {
    override fun isCancelled(): Boolean {
        return isCancelled
    }
    override fun setCancelled(p0: Boolean) {
        isCancelled = p0
    }
}