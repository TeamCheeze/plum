package io.github.teamcheeze.plum.api.core.entity

import org.bukkit.Location
import org.bukkit.util.Vector

/**
 * A movable entity
 * @author dolphin2410
 */
interface Movable<T>: BaseEntity {
    /**
     * Move a given length
     */
    fun move(delta: Vector, tick: (Location) -> Unit = {}): T

    /**
     * Move to a given point
     */
    fun moveTo(target: Location, tick: (Location) -> Unit = {}): T

    /**
     * Teleport to a given point
     */
    fun teleport(location: Location): T
}