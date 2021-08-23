package io.teamcheeze.plum.api.core.math

import org.bukkit.Location
import org.bukkit.util.EulerAngle
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

/**
 * Utilities about circle in minecraft. Mostly Math. BETA
 * @author dolphin2410
 */
class Circle {
    enum class CircleAxis{
        XY,
        YZ,
        XZ
    }
    enum class ThreeDimensionAxis{
        X,
        Y,
        Z
    }
    enum class AxisOrder{
        First,
        Second
    }
    companion object{
        private fun Double.floor(): Double {
            return kotlin.math.floor(this)
        }
        private fun Double.toRadians(): Double {
            return StrictMath.toRadians(this)
        }
        private fun Double.toDegrees(): Double {
            return StrictMath.toDegrees(this)
        }
        @JvmStatic
        private fun twoDimensionCircle(axisOrder: AxisOrder, rotationCounter: Double, radius: Double): Double{
            val rotation = when(rotationCounter){
                in (0 + Double.MIN_VALUE)..(90 - Double.MIN_VALUE)->Pair(radius * sin(rotationCounter.toRadians()), radius * cos(rotationCounter.toRadians()))
                90.0->Pair(radius, 0.0)
                in (90 + Double.MIN_VALUE)..(180 - Double.MIN_VALUE)-> Pair(radius * cos((rotationCounter - 90).toRadians()), -radius * sin((rotationCounter - 90).toRadians()))
                180.0->Pair(0.0, -radius)
                in (180 + Double.MIN_VALUE)..(270 - Double.MIN_VALUE)->Pair(-radius * sin((rotationCounter - 180).toRadians()), -radius * cos((rotationCounter - 180).toRadians()))
                270.0->Pair(-radius, 0.0)
                in (270 + Double.MIN_VALUE)..(360 - Double.MIN_VALUE)->Pair(-radius * cos((rotationCounter - 270).toRadians()), radius * sin((rotationCounter - 270).toRadians()))
                else->Pair(0.0, radius)
            }
            return if(axisOrder == AxisOrder.First){
                rotation.first
            } else {
                rotation.second
            }
        }


        @JvmStatic
        fun draw(radius: Double, center: Location, action: (Location)->Unit, rotation: EulerAngle = EulerAngle.ZERO, circleAxis: CircleAxis = CircleAxis.XY){
            var counter = 0.0
            when(circleAxis){
                CircleAxis.XY ->Pair(ThreeDimensionAxis.X, ThreeDimensionAxis.Y)
                CircleAxis.YZ ->Pair(ThreeDimensionAxis.Y, ThreeDimensionAxis.Z)
                CircleAxis.XZ ->Pair(ThreeDimensionAxis.X, ThreeDimensionAxis.Z)
            }
            val xRotation = rotation.x.toDegrees()
            val yRotation = rotation.y.toDegrees()
            val zRotation = rotation.z.toDegrees()
            var xCounter = xRotation.floor()
            var yCounter = yRotation.floor()
            var zCounter = zRotation.floor()

            while(counter != 360.0){

                when(counter){
                    in (0 + Double.MIN_VALUE)..(90 - Double.MIN_VALUE)->Pair(radius * sin(counter.toRadians()), radius * cos(counter.toRadians()))
                    90.0->Pair(radius, 0.0)
                    in (90 + Double.MIN_VALUE)..(180 - Double.MIN_VALUE)-> Pair(radius * cos((counter - 90).toRadians()), -radius * sin((counter - 90).toRadians()))
                    180.0->Pair(0.0, -radius)
                    in (180 + Double.MIN_VALUE)..(270 - Double.MIN_VALUE)->Pair(-radius * sin((counter - 180).toRadians()), -radius * cos((counter - 180).toRadians()))
                    270.0->Pair(-radius, 0.0)
                    in (270 + Double.MIN_VALUE)..(360 - Double.MIN_VALUE)->Pair(-radius * cos((counter - 270).toRadians()), radius * sin((counter - 270).toRadians()))
                    else->Pair(0.0, radius)
                }
                when(circleAxis){
                    CircleAxis.XY ->action.invoke(
                        center.clone().add(
                            Vector(
                                twoDimensionCircle(AxisOrder.First, xCounter, radius),
                                twoDimensionCircle(AxisOrder.Second, yCounter, radius),
                                0.0
                            )
                        )
                    )
                    CircleAxis.YZ ->action.invoke(
                        center.clone().add(
                            Vector(
                                0.0,
                                twoDimensionCircle(AxisOrder.First, yCounter, radius),
                                twoDimensionCircle(AxisOrder.Second, zCounter, radius)
                            )
                        )
                    )
                    CircleAxis.XZ ->action.invoke(
                        center.clone().add(
                            Vector(
                                twoDimensionCircle(AxisOrder.First, xCounter, radius),
                                0.0,
                                twoDimensionCircle(AxisOrder.Second, zCounter, radius)
                            )
                        )
                    )
                }
                xCounter += 1.0
                yCounter += 1.0
                zCounter += 1.0
                counter += 1.0
            }
        }
    }
}