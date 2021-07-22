package io.github.dolphin2410

import java.util.*

class OptionalTest {
    companion object {
        @JvmStatic
        fun main(args: Array<out String>) {
            println(Optional.empty<String>())
            println(Optional.ofNullable(null).isEmpty)
            println(Optional.ofNullable("HI").isEmpty)
            println(Optional.of("HI").isEmpty)
        }
    }
}