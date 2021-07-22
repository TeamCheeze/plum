package io.github.teamcheeze.plum.api.core.command

import java.util.*
import kotlin.collections.ArrayList

class CommandNode(
    private val sub: Int,
    private val path: ArrayList<Optional<String>>,
    private val execution: Array<out String>
) {
    fun input(action: CommandNode.(String) -> Unit) {
        val newNode = CommandNode(
            sub + 1,
            path.apply {
                add(0, Optional.empty<String>())
                    },
            execution
        )
        action.invoke(newNode, execution[sub])
    }

    fun option(name: String, action: CommandNode.() -> Unit) {
        val newNode = CommandNode(
            sub + 1,
            path.apply {
                add(0, Optional.of(name))
            },
            execution
        )
        if (execution[sub] == name) {
            action.invoke(newNode)
        }
    }

}