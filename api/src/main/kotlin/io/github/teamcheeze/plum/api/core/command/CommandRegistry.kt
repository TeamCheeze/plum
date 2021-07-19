package io.github.teamcheeze.plum.api.core.command

import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import io.github.dolphin2410.jaw.reflection.FieldAccessor
import io.github.dolphin2410.jaw.reflection.MethodAccessor
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.SimpleCommandMap
import org.bukkit.command.defaults.BukkitCommand
import kotlin.jvm.Throws

/**
 * A command registerer
 * @author dolphin2410
 */
class CommandRegistry {
    /**
     * Command context
     * @param sender The command sender
     * @param command The bukkit command
     * @param label The label of the command
     * @param args The arguments of the command
     */
    data class CommandContext(val sender: CommandSender, val command: Command, val label: String, val args: Array<out String>) {
        /**
         * An exception called to cancel the current method invocation
         */
        class Cancel: Exception()

        /**
         * Override the default equals checking system. It will return true if all the constructor content matches
         */
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as CommandContext
            if (sender != other.sender) return false
            if (command != other.command) return false
            if (label != other.label) return false
            if (!args.contentEquals(other.args)) return false
            return true
        }

        /**
         * The hashcode for the equal checking system
         */
        override fun hashCode(): Int {
            var result = sender.hashCode()
            result = 31 * result + command.hashCode()
            result = 31 * result + label.hashCode()
            result = 31 * result + args.contentHashCode()
            return result
        }

        /**
         * The state either the command succeeded
         */
        val success: Boolean = true

        /**
         * Declare when the execution fails
         * @param condition The condition when the execution is marked as failed
         */
        fun failIf(condition: () -> Boolean) {
            FieldAccessor(this, "success").set(!condition.invoke())
        }

        /**
         * Declare the action when the execution fails
         * @param action The action to run after the execution is marked as failed
         */
        @Throws(Cancel::class)
        fun failAction(action: () -> Unit) {
            if (!success) {
                action.invoke()
                throw Cancel()
            }
        }
    }
    companion object {
        /**
         * Registers the command
         * @param name The name of the command
         * @param usage The usage of the command that used to be declared in plugin.yml
         * @param description The description of the command that used to be declared in plugin.yml
         * @param aliases The aliases of the command that used to be declared in plugin.yml
         * @param action What the command will execute
         */
        @JvmStatic
        fun register(name: String, usage: String = "", description: String = "", aliases: Array<out String> = arrayOf(), action: CommandContext.() -> Unit) {
            val bukkitCommand = object: BukkitCommand(name) {
                init {
                    this.usageMessage = usage
                    this.description = description
                    this.aliases = aliases.toList()
                }
                override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
                    println("Executed")
                    val context = CommandContext(sender, this, label, args)
                    try {
                        action(context)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    if(!context.success){
                        return false
                    }
                    return true
                }
            }
            val accessor = MethodAccessor(MethodAccessor(GBukkit.server, "getCommandMap").invoke()!!, "register")
            accessor.setDeclaringClass(CommandMap::class.java)
            accessor.invoke(name, bukkitCommand)
        }
    }
}