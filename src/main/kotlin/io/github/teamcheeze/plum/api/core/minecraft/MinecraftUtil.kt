package io.github.teamcheeze.plum.api.core.minecraft

import io.github.dolphin2410.jaw.reflection.ConstructorAccessor
import io.github.dolphin2410.jaw.reflection.FieldAccessor
import io.github.dolphin2410.jaw.reflection.MethodAccessor
import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import io.github.teamcheeze.plum.api.core.minecraft.network.Packet
import io.github.teamcheeze.plum.api.core.minecraft.network.PacketImpl
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * Some core reflection features
 * @author dolphin2410
 */
class MinecraftUtil {
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
        @JvmStatic
        val commandMap: CommandMap = MethodAccessor(GBukkit.server, "getCommandMap").invoke() as CommandMap
        @JvmStatic
        fun syncCommands() {
            MethodAccessor(GBukkit.server, "syncCommands").invoke()
            Bukkit.getOnlinePlayers().forEach {
                it.updateCommands()
            }
        }
        @JvmStatic
        fun sendPacket(player: Player, packet: Packet) {
            val playerConnection = FieldAccessor(getHandle(player), "b").get()
            MethodAccessor(playerConnection, "sendPacket").invoke(packet.handle)
        }
//        @JvmStatic
//        fun launchProjectile(player: Player, entity: Entity) {
//            val snowball = player.launchProjectile(Snowball::class.java)
//            snowball.addPassenger(entity)
//            sendPacket(player, removePacket(snowball))
//        }
        @JvmStatic
        fun removePacket(entity: Entity): Packet {
            val accessor = ConstructorAccessor(Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy"))
            when (true) {
                Bukkit.getVersion().contains("(MC: 1.17.1)") -> {
                    return PacketImpl(accessor.newInstance(intArrayOf(entity.entityId)))
                }
                else -> {
                    throw RuntimeException("This feature isn't supported yet")
                }
            }
        }
    }
}