package io.github.teamcheeze.plum.api.core.command

import io.github.teamcheeze.plum.api.core.minecraft.MinecraftUtil
import io.github.teamcheeze.plum.api.util.annotations.SafeDSL

/**
 * @author dolphin2410
 */
class CommandRegistry {
    @SafeDSL
    companion object {
        /**
         * A simple, light-weight way of registering a command. This is inspired by [Monun](https://github.com/Monun)'s [kommand](https://github.com/Monun/Kommand).
         *
         * ***Features***
         * - Simple and LightWeight: The CommandRegistry feature doesn't depend on other dependencies except the spigot-api
         * - Version Independent: This neither would cause nms-related issues nor limit server owners to only use provided versions
         * - Usage of APIs: The CommandRegistry feature is created only with the use of Spigot-Api and a line of reflection with craftbukkit
         * @param name The name of the command
         * @param dsl The command registry dsl
         */
        @JvmStatic
        fun register(name: String, dsl: (@SafeDSL CommandNode).() -> Unit): Commander {
            return Commander(name).apply {
                execution.set { args, sender -> dsl.invoke(CommandNode(arrayListOf(), this, args, 0, sender)) }
                MinecraftUtil.commandMap.register(name, this)
                // This is the regex that matches <group>:<namespace>
                if (Regex("^.+:.+$").matches(name)) {
                    val split = name.split(":", limit = 1)
                    MinecraftUtil.commandMap.register(split[0], split[1], this)
                } else {
                    MinecraftUtil.commandMap.register(name, this)
                }
                MinecraftUtil.syncCommands()
            }
        }
    }
}