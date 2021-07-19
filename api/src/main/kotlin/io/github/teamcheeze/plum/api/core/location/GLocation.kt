package io.github.teamcheeze.plum.api.core.location

import io.github.teamcheeze.plum.api.util.Deserializable
import io.github.teamcheeze.plum.api.util.Serializable
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.NumberFormatException
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.floor

/**
 * A location wrapper with core math operator features. Also contains a serializing and deserializing feature.
 * @author dolphin2410
 */
class GLocation(
    val world: World,
    var x: Number,
    var y: Number,
    var z: Number,
    var yaw: Number = 0.0,
    var pitch: Number = 0.0
): Serializable {
    private fun Double.floor(): Double {
        return floor(this)
    }
    private fun Double.toRadians(): Double {
        return StrictMath.toRadians(this)
    }
    companion object: Deserializable<GLocation> {
        /**
         * Deserializes the given string
         * @param str The serialized string location
         * @return The deserialized GLocation
         * @throws Deserializable.SerializationTypeMismatchException
         * @throws Deserializable.InvalidSerializedStringException
         * @throws Deserializable.InvalidSerializationParameterException
         */
        @Throws(
            Deserializable.SerializationTypeMismatchException::class,
            Deserializable.InvalidSerializedStringException::class,
            Deserializable.InvalidSerializationParameterException::class
        )
        override fun deserialize(str: String): GLocation {
            return try {
                val splitArray = str.split(";")
                val serializedType = splitArray[0]
                val serializedWorldName = splitArray[1]
                val serializedX = splitArray[2].toDouble()
                val serializedY = splitArray[3].toDouble()
                val serializedZ = splitArray[4].toDouble()
                val serializedYaw = splitArray[5].toFloat()
                val serializedPitch = splitArray[6].toFloat()
                if(serializedType != GLocation::class.java.name){
                    throw Deserializable.SerializationTypeMismatchException("Mismatching current class and serialized class")
                }
                GLocation(Bukkit.getWorld(serializedWorldName)!!, serializedX, serializedY, serializedZ, serializedYaw, serializedPitch)
            }
            catch (e: IndexOutOfBoundsException) {
                throw Deserializable.InvalidSerializedStringException(e.stackTraceToString())
            }
            catch (e: NumberFormatException) {
                throw Deserializable.InvalidSerializationParameterException(e.stackTraceToString())
            }
        }
    }

    /**
     * The z location of the block in the location
     */
    val blockZ: Int
        get() = y.toDouble().floor().toInt()

    /**
     * The y location of the block in the location
     */
    val blockY: Int
        get() = y.toDouble().floor().toInt()

    /**
     * The x location of the block in the location
     */
    val blockX: Int
        get() = x.toDouble().floor().toInt()

    /**
     * Create a GLocation instance with a bukkit [Location] class
     */
    constructor(location: Location): this(location.world!!, location.x, location.y, location.z, location.yaw, location.pitch)

    /**
     * Floors all the x, y and z
     */
    fun abstract() {
        this.x = blockX
        this.y = blockY
        this.z = blockZ
    }

    /**
     * Getting the direction. Copied the code from the bukkit [Location] class
     */
    val direction: Vector
        get() {
            return Vector().apply {
                val rotX: Double = yaw.toDouble()
                val rotY: Double = pitch.toDouble()
                this.y = -sin(Math.toRadians(rotY))
                val xz = cos(rotY.toRadians())
                this.x = -xz * sin(rotX.toRadians())
                this.z = xz * cos(rotX.toRadians())
            }
        }

    /**
     * Converts the current yaw to degrees
     * @return The degreefied yaw
     */
    fun yawToDegrees(): Double{
        val toReturn = if(yaw.toFloat() <= 0){
            (yaw.toFloat() + 360).toDouble()
        } else {
            yaw.toDouble()
        }
        val isValid = toReturn <= 360
        return if(isValid){
            toReturn
        } else {
            throw Exception("YOLO INVALID YAW")
        }
    }

    /**
     * Converts the GLocation to location
     * @return The bukkit [Location]
     */
    fun toLocation(): Location {
        return Location(world, x.toDouble(), y.toDouble(), z.toDouble(), yaw.toFloat(), pitch.toFloat())
    }

    /**
     * Converts the GLocation to vector
     * @return The bukkit [Vector]
     */
    fun toVector(): Vector {
        return Vector(x.toDouble(), y.toDouble(), z.toDouble())
    }

    /**
     * Serializing the GLocation
     * @return The serialized String
     */
    override fun serialize(): String {
        return "${GLocation::class.java.name};${world.name};${x};${y};${z};${yaw};${pitch}"
    }
}