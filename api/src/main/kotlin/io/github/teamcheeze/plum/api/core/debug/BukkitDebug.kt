package io.github.teamcheeze.plum.api.core.debug

import io.github.teamcheeze.plum.api.core.alert.BukkitAlert
import io.github.teamcheeze.plum.api.util.core.Property

/**
 * The bukkit debugging class. Easily convert from development to production mode without deleting the debug actions by switching the 'debug' field
 * @author dolphin2410
 */
class BukkitDebug {
    companion object {
        /**
         * The option either the debugging mode is on
         */
        @JvmStatic
        var debug = Property(false)

        /**
         * Executes a given block when the program is running in debug mode
         */
        @JvmStatic
        fun execute(block: ()->Unit){
            if(debug.get()){
                block.invoke()
            }
        }

        /**
         * Logs a statement to the console if the program is running in debug mode
         */
        @JvmStatic
        fun log(msg: String) {
            if(debug.get()) {
                BukkitAlert.debug(msg)
            }
        }
    }
}