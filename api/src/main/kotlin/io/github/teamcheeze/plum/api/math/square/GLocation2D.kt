package io.github.teamcheeze.plum.api.math.square

import org.bukkit.World

data class GLocation2D(val world: World, val x: Double, val y: Double) {
    fun toVector(): GVector2D = GVector2D(x, y)
}