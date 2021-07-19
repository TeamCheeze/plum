package io.github.teamcheeze.plum.api.core.events.manager

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * An event registerer
 * @author dolphin2410
 */
class EventRegistry {
    companion object {
        /**
         * Listen to the event
         * @param action The action that will be fired with the event
         */
        @JvmStatic
        inline fun <reified T: Event> register(noinline action: (T)->Unit){
            val listenerWrapper = RegisteredListenerWrapper(action)
            try {
                val handlers = T::class.java.methods.find { it.name == "getHandlerList" }!!.apply { isAccessible = true }.invoke(null) as HandlerList
                handlers.register(listenerWrapper)
            }
            catch (e: NullPointerException) {
                e.printStackTrace()
            }
            catch (e: ClassCastException) {
                e.printStackTrace()
            }
        }
    }
}