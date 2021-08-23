package io.teamcheeze.plum.api.core.bukkit

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
        fun run(action: ()->Unit){
            object: BukkitRunnable(){
                override fun run() {
                    action.invoke()
                }
            }.runTask(io.teamcheeze.plum.api.PluginLoader.plugin)
        }
    }
}