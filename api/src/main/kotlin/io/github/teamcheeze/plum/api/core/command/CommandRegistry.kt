package io.github.teamcheeze.plum.api.core.command

import io.github.dolphin2410.jaw.reflection.MethodAccessor
import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import org.bukkit.command.CommandMap

class CommandRegistry {
    companion object {
        @JvmStatic
        fun register(name: String, action: CommandNode.()->Unit): Commander {
            val command = Commander(name)
            command.callback.set { args, sender -> action.invoke(CommandNode(arrayListOf(), command, args, 0, sender)) }
            val accessor = MethodAccessor(MethodAccessor(GBukkit.server, "getCommandMap").invoke()!!, "register")
            accessor.setDeclaringClass(CommandMap::class.java)
            accessor.invoke(name, command)
            return command
        }
    }
}