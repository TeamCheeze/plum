package io.github.teamcheeze.plum.api.core.entity

import org.bukkit.entity.Entity
import java.util.*

/**
 * The base custom entity interface
 * @author dolphin2410
 */
interface BaseEntity{
    /**
     * The id of the custom entity
     */
    val id: Int

    /**
     * The uuid of the custom entity
     */
    val uniqueId: UUID

    /**
     * The bukkit entity variant of the custom entity
     */
    val bukkitEntity: Entity

    /**
     * Updating the custom entity's metadata
     */
    fun updateMetadata()

    /**
     * Updating the bukkit entity's properties
     */
    fun <T: Entity>update(action: T.() -> Unit) {
        @Suppress("unchecked_cast")
        action.invoke(this.bukkitEntity as T)
        updateMetadata()
    }

    /**
     * Removal of the entity
     */
    fun remove()
}