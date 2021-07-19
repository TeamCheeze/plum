package io.github.teamcheeze.plum.api.math.square

import kotlin.math.cos
import kotlin.math.sin

class GCircle2D {
    private fun Double.toRadians(): Double {
        return StrictMath.toRadians(this)
    }
    fun getCoordinate2D(radius: Double, angle: Double): GVector2D {
        return GVector2D(radius * cos(angle.toRadians()), radius * sin(angle.toRadians()))
    }
    fun rotateZ(degrees: Double) {

    }
}