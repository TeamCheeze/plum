package io.github.teamcheeze.plum.api.core.bukkit

import io.github.teamcheeze.plum.api.PluginLoader
import org.bukkit.scheduler.BukkitRunnable

/**
 * For synchronous bukkit actions in an asynchronous thread.
 * @author dolphin2410
 */
class BukkitSync {
    companion object{
        /**
         * This method runs the passed function in the main thread using BukkitRunnable
         * @param action The action to be called
         */
        @JvmStatic
        fun run(action: ()->Any){
            object: BukkitRunnable(){
                override fun run() {
                    action.invoke()
                }
            }.runTask(io.github.teamcheeze.plum.api.PluginLoader.plugin)
        }
    }
}