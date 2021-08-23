package io.teamcheeze.plum.api.core.world

import com.mojang.authlib.GameProfile
import io.github.teamcheeze.plum.api.auth.GameProfileWrapper
import io.github.teamcheeze.plum.api.core.entity.BaseEntity
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

/**
 * An entity spawnable interface
 * @author dolphin2410
 */
interface EntitySpawnable<T: BaseEntity> {
    /**
     * Total entities in this spawnable place
     */
    val entities: ArrayList<T>

    /**
     * The bukkit world instance of this place
     */
    val bukkitWorld: World

    /**
     * Spawning NPCs
     */
    fun spawnNpc(
        location: Location,
        profile: GameProfile
    ): T

    /**
     * Spawning NPCs with [GameProfileWrapper]
     */
    fun spawnNpc(
        location: Location,
        profile: GameProfileWrapper
    ): T

    /**
     * Spawning default entities from entity class
     */
    fun <E: Entity>spawn(
        location: Location,
        clazz: Class<E>,
    ): T

    /**
     * Spawning default entities from entity type
     */
    fun spawnEntity(
        location: Location,
        entityType: EntityType
    ): T

    /**
     * Drop an item
     */
    fun dropItem(
        location: Location,
        item: ItemStack
    ): T

    /**
     * Spawn a falling block
     */
    fun spawnFallingBlock(
        location: Location,
        blockData: BlockData,
    ): T
}