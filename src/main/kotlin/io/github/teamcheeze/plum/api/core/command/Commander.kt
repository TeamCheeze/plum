package io.github.teamcheeze.plum.api.core.command

import io.github.dolphin2410.jaw.reflection.FieldAccessor
import io.github.teamcheeze.plum.api.core.minecraft.MinecraftUtil
import io.github.teamcheeze.plum.api.util.collection.matches
import io.github.teamcheeze.plum.api.util.core.Property
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.SimpleCommandMap
import org.bukkit.command.defaults.BukkitCommand
import java.util.*

class Commander(name: String) : BukkitCommand(name) {
    val execution: Property<(Optional<Array<out String>>, CommandSender) -> Unit> = Property { _, _ -> }
    val registry = ArrayList<Pair<ArrayList<String>, Pair<() -> Unit, ArrayList<String>>>>()
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        execution.get().invoke(Optional.of(args), sender)
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        execution.get().invoke(Optional.empty<Array<out String>>(), sender)
        return registry.find {
            matches(
                it.first,
                ArrayList(args.toList()).apply { removeLastOrNull() },
                ""
            )
        }?.second?.second ?: ArrayList()
    }

    var isValid = true

    @Suppress("unchecked_cast")
    fun remove() {
        (FieldAccessor(MinecraftUtil.commandMap, "knownCommands").setDeclaringClass(SimpleCommandMap::class.java)
            .get() as MutableMap<String, Command>).remove(name)
        isValid = false
    }
}