package io.github.teamcheeze.plum.api.math.cube

import io.github.teamcheeze.plum.api.util.Cloneable
import io.github.dolphin2410.jaw.reflection.FieldAccessor
import org.bukkit.util.Vector

data class GVector(val x: Double, val y: Double, val z: Double): Cloneable<GVector> {
    companion object {
        @JvmStatic
        val ZERO = GVector(0.0, 0.0, 0.0)
    }

    override fun clone(): GVector {
        return GVector(x, y, z)
    }

    val bukkitVector: Vector
        get() = Vector(x, y, z)

    private val XAccessor = FieldAccessor(this, "x")
    private val YACcessor = FieldAccessor(this, "y")
    private val ZAccessor = FieldAccessor(this, "z")

    operator fun plus(other: GVector): GVector {
        XAccessor.set(x + other.x)
        YACcessor.set(y + other.y)
        ZAccessor.set(z + other.z)
        return this
    }
    operator fun minus(other: GVector): GVector {
        XAccessor.set(x - other.x)
        YACcessor.set(y - other.y)
        ZAccessor.set(z - other.z)
        return this
    }
    operator fun times(other: GVector): GVector {
        XAccessor.set(x * other.x)
        YACcessor.set(y * other.y)
        ZAccessor.set(z * other.z)
        return this
    }
    operator fun times(other: Double): GVector {
        XAccessor.set(x * other)
        YACcessor.set(y * other)
        ZAccessor.set(z * other)
        return this
    }
    operator fun div(other: Double): GVector {
        XAccessor.set(x / other)
        YACcessor.set(y / other)
        ZAccessor.set(z / other)
        return this
    }
    operator fun div(other: GVector): GVector {
        XAccessor.set(x / other.x)
        YACcessor.set(y / other.y)
        ZAccessor.set(z / other.z)
        return this
    }
    operator fun plusAssign(other: GVector) {
        plus(other)
    }
    operator fun minusAssign(other: GVector) {
        minus(other)
    }
    operator fun divAssign(other: GVector) {
        div(other)
    }
    operator fun divAssign(other: Double) {
        div(other)
    }
    operator fun timesAssign(other: GVector) {
        times(other)
    }
    operator fun timesAssign(other: Double) {
        times(other)
    }
    fun add(other: GVector) = plus(other)
    fun subtract(other: GVector) = minus(other)
    fun multiply(other: GVector) = times(other)
    fun divide(other: GVector) = div(other)
    fun multiply(other: Double) = times(other)
    fun divide(other: Double) = div(other)

}