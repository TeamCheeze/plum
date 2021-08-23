package io.teamcheeze.plum.api.core.math.cube

import kotlin.math.cos
import kotlin.math.sin

class GCircle {
    val dots = HashSet<GCirclePoint>()
    fun rotate(radius: Double, rotation: GRotation): GVector {
        val initialVector = GVector.ZERO
        initialVector += GVector(0.0, radius * sin(rotation.x), -radius * (1 - cos(rotation.x)))
        initialVector += GVector(-radius * (1 - cos(rotation.y)), 0.0, radius * sin(rotation.y))
        initialVector += GVector(-radius * (1- cos(rotation.z)), radius * sin(rotation.z), 0.0)
        val iterator = dots.iterator()
        while (iterator.hasNext()) {

        }
        return initialVector
    }
}