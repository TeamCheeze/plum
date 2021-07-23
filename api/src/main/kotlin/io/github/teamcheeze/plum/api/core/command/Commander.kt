package io.github.teamcheeze.plum.api.core.command

import io.github.teamcheeze.plum.api.util.collection.matches
import io.github.teamcheeze.plum.api.util.core.Property
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import java.util.*

class Commander(name: String): BukkitCommand(name) {
    // TODO Error if sender doesn't pass an argument for input. Warning message for that
    val callback: Property<(Optional<Array<out String>>, CommandSender) -> Unit> = Property { _, _->}
    val registry = ArrayList<Pair<ArrayList<String>, Pair<()->Unit, ArrayList<String>>>>()
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        callback.get().invoke(Optional.of(args), sender)
        return true
    }
    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        callback.get().invoke(Optional.empty<Array<out String>>(), sender)
        return registry.find { matches(it.first, ArrayList(args.toList()).apply { removeLast() }, "") }?.second?.second ?: ArrayList()
    }
}