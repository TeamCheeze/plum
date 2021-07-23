package io.github.dolphin2410

import io.github.teamcheeze.plum.api.util.minex.Minex

class MinexTest {
    companion object {
        @JvmStatic
        fun main(args: Array<out String>) {
            val minex = Minex.of("#(_#)_R#")
            minex[0] = "1"
            minex[0] = "17"
            minex[0] = "1"
            println(minex)
        }
    }
}