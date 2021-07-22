package io.github.teamcheeze.plum.api.core.command

import io.github.dolphin2410.jaw.reflection.MethodAccessor
import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import io.github.teamcheeze.plum.api.util.PlumAnnotations.Since
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import java.util.*

@Since("0.0.4")
class PCommand(name: String, private val node: Array<out String>.() -> CommandNode) : BukkitCommand(name) {
    companion object {
        @JvmStatic
        fun register(name: String, node: CommandNode.() -> Unit) {
            val accessor = MethodAccessor(MethodAccessor(GBukkit.server, "getCommandMap").invoke()!!, "register")
            accessor.setDeclaringClass(CommandMap::class.java)
            accessor.invoke(name, PCommand(name) {
                CommandNode(0, arrayListOf(Optional.empty<String>()), this).also { node.invoke(it) }
            })
        }
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        node.invoke(args)
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        return super.tabComplete(sender, alias, args)
    }
}