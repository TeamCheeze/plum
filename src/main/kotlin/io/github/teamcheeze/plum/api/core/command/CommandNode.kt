package io.github.teamcheeze.plum.api.core.command

import io.github.teamcheeze.plum.api.util.core.Property
import org.bukkit.command.CommandSender
import java.util.*
import kotlin.collections.ArrayList

class CommandNode(
    private val path: ArrayList<String>,
    private val command: Commander,
    private val args: Optional<Array<out String>>,
    private val index: Int,
    val sender: CommandSender
) {
    @Suppress("unchecked_cast")
    fun input(action: CommandNode.(String) -> Unit) {
        val newBanana = CommandNode(
            (path.clone() as ArrayList<String>).apply { add("") },
            command,
            args,
            index + 1,
            sender
        )
        if (args.isEmpty) {
            action.invoke(newBanana, "")
        } else {
            if (args.get().isNotEmpty()) {
                action.invoke(newBanana, args.get()[index])
            }
        }
        (command.registry.find { it.first == path } ?: Pair(
            path,
            Pair(execution.get(), arrayListOf<String>())
        ).also { command.registry.add(it) })
            .second.second.add("")
    }

    fun <T: Enum<T>>options(clazz: Class<T>, action: CommandNode.(T) -> Unit) {
        val actionAfterIteration = ArrayList<()->Unit>()
        clazz.enumConstants.forEach {
            actionAfterIteration.add {
                option(it.name) {
                    action.invoke(this, it)
                }
            }
        }
        actionAfterIteration.forEach {
            it.invoke()
        }
    }


    fun <T: Enum<T>>options(options: Collection<T>, action: CommandNode.(T)->Unit) {
        val actionAfterIteration = ArrayList<()->Unit>()
        options.forEach {
            actionAfterIteration.add {
                option(it.name) {
                    action.invoke(this, it)
                }
            }
        }
        actionAfterIteration.forEach {
            it.invoke()
        }
    }

    fun <T: Enum<T>>options(options: Array<T>, action: CommandNode.(T)->Unit) {
        val actionAfterIteration = ArrayList<()->Unit>()
        options.forEach {
            actionAfterIteration.add {
                option(it.name) {
                    action.invoke(this, it)
                }
            }
        }
        actionAfterIteration.forEach {
            it.invoke()
        }
    }

    @JvmName("stringOptions")
    fun options(options: Collection<String>, action: CommandNode.(String) -> Unit) {
        val actionAfterIteration = ArrayList<()->Unit>()
        options.forEach {
            actionAfterIteration.add {
                option(it) {
                    action.invoke(this, it)
                }
            }
        }
        actionAfterIteration.forEach {
            it.invoke()
        }
    }

    fun options(options: Array<out String>, action: CommandNode.(String) -> Unit) {
        options(ArrayList(options.toList()), action)
    }


//    fun options(options: List<String>, action: CommandNode.(String) -> Unit) {
//
//    }

    val execution: Property<() -> Unit> = Property {}
    fun executes(action: () -> Unit) {
        if (!args.isEmpty) {
            execution.set(action)
            action.invoke()
        }
    }

    @Suppress("unchecked_cast")
    fun option(name: String, action: CommandNode.() -> Unit) {
        val newBanana = CommandNode(
            (path.clone() as ArrayList<String>).apply { add(name) },
            command,
            args,
            index + 1,
            sender
        )
        if (args.isEmpty) {
            action.invoke(newBanana)
        } else {
            if (args.get().isNotEmpty()) {
                if (args.get()[index] == name) {
                    action.invoke(newBanana)
                }
            }
        }
        (command.registry.find { it.first == path } ?: Pair(
            path,
            Pair(execution.get(), arrayListOf<String>())
        ).also { command.registry.add(it) })
            .second.second.add(name)
    }
}