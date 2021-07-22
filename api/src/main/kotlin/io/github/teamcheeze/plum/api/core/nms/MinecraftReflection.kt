package io.github.teamcheeze.plum.api.core.nms

import io.github.dolphin2410.jaw.reflection.MethodAccessor
import org.bukkit.entity.Entity
import kotlin.jvm.Throws

/**
 * Some core reflection features
 * @author dolphin2410
 */
class MinecraftReflection {
    /**
     * When an illegal field of an NMS module is accessed
     */
    class NoNMSValueFoundException(msg: String): RuntimeException(msg)
    companion object {
        /**
         * Gets the NMS entity of a bukkit entity
         * @throws NoNMSValueFoundException
         */
        @Throws(NoNMSValueFoundException::class)
        @JvmStatic
        fun getHandle(entity: Entity): Any {
            return MethodAccessor(entity::class.java.cast(entity), "getHandle").invoke() ?: throw NoNMSValueFoundException("This isn't supposed to happen. Please create an issue on github with EntityType: ${entity.type}")
        }
    }
}