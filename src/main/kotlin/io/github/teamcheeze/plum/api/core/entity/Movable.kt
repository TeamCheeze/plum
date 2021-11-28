package io.github.teamcheeze.plum.api.core.entity

import org.bukkit.Location
import org.bukkit.util.Vector
import java.util.concurrent.CompletableFuture

/**
 * A movable entity
 * @author dolphin2410
 */
interface Movable<T>: BaseEntity {
    /**
     * Move a given length
     */
    fun move(delta: Vector, speed: Double = 1.0, tick: (Location) -> Unit = {}): CompletableFuture<Location>

    /**
     * Move to a given point
     */
    fun moveTo(target: Location, speed: Double = 1.0, tick: (Location) -> Unit = {}): CompletableFuture<Location>

    /**
     * Teleport to a given point
     */
    fun teleport(location: Location): T
}