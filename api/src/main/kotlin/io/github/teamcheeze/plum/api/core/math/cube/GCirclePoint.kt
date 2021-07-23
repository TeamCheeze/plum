package io.github.teamcheeze.plum.api.core.math.cube

import io.github.teamcheeze.plum.api.core.entity.Movable
import org.bukkit.Location

data class GCirclePoint(val location: GVector, val element: Movable<*>) {
    fun move(vector: GVector, actionEachTick: (Location)->Unit = {}) {
        element.move(vector.bukkitVector, actionEachTick)
    }
}