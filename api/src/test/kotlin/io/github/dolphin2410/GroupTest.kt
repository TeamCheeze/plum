package io.github.dolphin2410

import io.github.teamcheeze.plum.api.util.core.Group

class GroupTest {
    companion object {
        @JvmStatic
        fun main(args: Array<out String>) {
            val groupA = Group("groupA")
            val groupB = Group("groupB")
            val groupAA = groupA.addGroup("groupAA")
            val groupAB = groupA.addGroup("groupAB")
            println(groupA == groupAA.parent)
        }
    }
}