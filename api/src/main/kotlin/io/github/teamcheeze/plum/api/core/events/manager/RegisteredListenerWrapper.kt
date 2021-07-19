package io.github.teamcheeze.plum.api.core.events.manager

import io.github.teamcheeze.plum.api.PluginLoader
import io.github.dolphin2410.jaw.reflection.FieldAccessor
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.RegisteredListener

/**
 * An overriden Bukkit's [RegisteredListener]
 * @author dolphin2410
 * @param action The action that will be fired with the event
 */
class RegisteredListenerWrapper<T: Event>(action: T.()->Unit): RegisteredListener(object: Listener{},
    EventExecutorWrapper(action), EventPriority.NORMAL, io.github.teamcheeze.plum.api.PluginLoader.plugin, false) {
    /**
     * A class that will be called when trying to execute from an non wrapped EventExecutor
     */
    class UnwrappedEventExecutorException(msg: String): Exception(msg)

    /**
     * Calling the event
     * @param event The event instance that is called
     */
    override fun callEvent(event: Event) {
        callEventWrapper(event)
    }

    /**
     * A wrapped call event method. Executes a overriden EventExecutor
     * @param event The event instance that is called
     */
    private fun <T: Event> callEventWrapper(event: T) {
        /**
         * This should have been called from Registered listener. My bad.
         */
        val executor = FieldAccessor(this, "executor").get() as EventExecutor
        try {
            @Suppress("unchecked_cast")
            val executorWrapper = executor as EventExecutorWrapper<T>
            executorWrapper.execute(event)
        }
        catch (e: ClassCastException) {
            throw UnwrappedEventExecutorException(e.toString())
        }
    }
}