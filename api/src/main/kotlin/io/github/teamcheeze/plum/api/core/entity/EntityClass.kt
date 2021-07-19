package io.github.teamcheeze.plum.api.core.entity

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

/**
 * Entity Type and Class conversion
 * @author dolphin2410
 */
class EntityClass {
    companion object {
        /**
         * From type to class
         * @param type The entity type
         */
        @JvmStatic
        fun fromType(type: EntityType): Class<out Entity> {
                return type.entityClass ?: throw NullPointerException("No entity class found for the type")
        }

        /**
         * From class to type
         * @param clazz The entity class
         */
        @JvmStatic
        fun fromClass(clazz: Class<out Entity>): EntityType {
            return EntityType.values().find { it.entityClass == clazz } ?: throw NullPointerException("No entity type found for the class")
        }
    }
}