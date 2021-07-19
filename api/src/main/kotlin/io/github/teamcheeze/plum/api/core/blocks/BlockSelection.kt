package io.github.teamcheeze.plum.api.core.blocks

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import java.lang.Double.max
import java.lang.Double.min

/**
 * Selecting blocks between two locations
 * @author dolphin2410
 */
class BlockSelection(private val world: World, loc1: Location, loc2 : Location) {
    private val x1loc : Double = loc1.x
    private val x2loc : Double = loc2.x
    private val y1loc : Double = loc1.y
    private val y2loc : Double = loc2.y
    private val z1loc : Double = loc1.z
    private val z2loc : Double = loc2.z

    var x1 : Int = min(x1loc,x2loc).toInt()
    var x2 : Int = max(x1loc,x2loc).toInt()
    var y1 : Int = min(y1loc,y2loc).toInt()
    var y2 : Int = max(y1loc,y2loc).toInt()
    var z1 : Int = min(z1loc,z2loc).toInt()
    var z2 : Int = max(z1loc,z2loc).toInt()

    fun getBlocks() : ArrayList<Block> {
        val toReturn = ArrayList<Block>()

        for(a in this.x1..this.x2){
            for(b in this.y1..this.y2){
                for(c in this.z1..this.z2){
                    val currentTargetLocation = Location(world,a.toDouble(),b.toDouble(),c.toDouble());
                    toReturn.add(world.getBlockAt(currentTargetLocation));
                }
            }
        }
        return toReturn;
    }
}