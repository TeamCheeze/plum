package io.github.teamcheeze.plum.api.core.blocks

import io.github.teamcheeze.plum.api.core.location.GLocation
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

/**
 * Utilities for blocks
 * @author dolphin2410
 */
class BlocksManager {
    companion object {
        @JvmStatic
        fun getMiddleBlock(blockArray: ArrayList<Block>): Location {
            val xList = blockArray.filter { it.location.y == blockArray[0].location.y }
                .filter { it.location.z == blockArray[0].location.z }
            val yList = blockArray.filter { it.location.x == blockArray[0].location.x }
                .filter { it.location.z == blockArray[0].location.z }
            val zList = blockArray.filter { it.location.x == blockArray[0].location.x }
                .filter { it.location.y == blockArray[0].location.y }
            val xMaxIndex = xList.size - 1
            val yMaxIndex = yList.size - 1
            val zMaxIndex = zList.size - 1
            val world: World = xList[0].location.world!!
            val xCenterLocation = if (xList.size % 2 == 1) {
                xList[(xMaxIndex) / 2].location.add(0.5, 0.0, 0.5).x
            } else {
                val one = xList[((xList.size) / 2) - 1].location.add(0.5, 0.0, 0.5)
                val two = xList[(xList.size) / 2].location.add(0.5, 0.0, 0.5)
                (one.x + two.x) / 2
            }
            val yCenterLocation = if (yList.size % 2 == 1) {
                yList[(yMaxIndex) / 2].location.y
            } else {
                val one = yList[((yList.size) / 2) - 1].location.add(0.5, 0.0, 0.5)
                val two = yList[(yList.size) / 2].location.add(0.5, 0.0, 0.5)
                (one.y + two.y) / 2
            }
            val zCenterLocation = if (xList.size % 2 == 1) {
                zList[(zMaxIndex) / 2].location.add(0.5, 0.0, 0.5).z
            } else {
                val one = zList[((zList.size) / 2) - 1].location.add(0.5, 0.0, 0.5)
                val two = zList[(zList.size) / 2].location.add(0.5, 0.0, 0.5)
                (one.z + two.z) / 2
            }
            return Location(world, xCenterLocation, yCenterLocation, zCenterLocation)

        }

        @JvmStatic
        fun reset(plugin: Plugin, player: Player) {
            val dataContainer = player.persistentDataContainer
            val nameSpacedBlockBreakNum = NamespacedKey(plugin, "blockBreakNum")
            val nameSpacedBlockBreakLocation = NamespacedKey(plugin, "blockBreakLocation")
            if (dataContainer.has(nameSpacedBlockBreakLocation, PersistentDataType.STRING)) {
                dataContainer.remove(nameSpacedBlockBreakLocation)
            }
            if (dataContainer.has(nameSpacedBlockBreakNum, PersistentDataType.INTEGER)) {
                dataContainer.remove(nameSpacedBlockBreakNum)
            }
        }

        @JvmStatic
        fun blockSelect(plugin: Plugin, player: Player, brokenBlock: Block, callback: BlockCallback) {

            //set dataContainer to player's persistentDataContainer
            val dataContainer: PersistentDataContainer = player.persistentDataContainer

            //create a namespacedKey of how many blocks player broke
            val nameSpacedBlockBreakNum = NamespacedKey(plugin, "blockBreakNum")

            //create a namespacedKey of the block location player broke
            val nameSpacedBlockBreakLocation = NamespacedKey(plugin, "blockBreakLocation")

            //if dataContainer has a blockBreakNum
            if (dataContainer.has(nameSpacedBlockBreakNum, PersistentDataType.INTEGER)) {

                //if one block is already clicked and stored
                if (dataContainer.get(nameSpacedBlockBreakNum, PersistentDataType.INTEGER) == 1) {

                    //reset block break num
                    dataContainer.set(nameSpacedBlockBreakNum, PersistentDataType.INTEGER, 0)

                } else if (dataContainer.get(nameSpacedBlockBreakNum, PersistentDataType.INTEGER) == 0) {

                    //first block is not set yet
                    dataContainer.set(nameSpacedBlockBreakNum, PersistentDataType.INTEGER, 1)
                }

                //if block break num isn't initialized
            } else {

                //first block is not set yet
                dataContainer.set(nameSpacedBlockBreakNum, PersistentDataType.INTEGER, 1)
            }

            //if block location key is initialized
            if (dataContainer.has(nameSpacedBlockBreakLocation, PersistentDataType.STRING)) {

                //if clicked first block
                if (dataContainer.get(nameSpacedBlockBreakNum, PersistentDataType.INTEGER) == 1) {

                    callback.onFirstWandSwing(brokenBlock)

                    //save its location to the container
                    dataContainer.set(
                        nameSpacedBlockBreakLocation,
                        PersistentDataType.STRING,
                        GLocation(brokenBlock.location).serialize()
                    )

                    //clicked second block
                } else if (dataContainer.get(nameSpacedBlockBreakNum, PersistentDataType.INTEGER) == 0) {

                    //parse the first stored string location
                    val firstBlockLocation = GLocation.deserialize(dataContainer.get(nameSpacedBlockBreakLocation, PersistentDataType.STRING)!!).toLocation()

                    //set second block location to current broken block location
                    val secondBlockLocation = brokenBlock.location


                    //set selectedBlocks to ArrayList of blocks between two locations
                    val selectedBlocks: ArrayList<Block> =
                        BlockSelection(player.world, firstBlockLocation, secondBlockLocation).getBlocks()

                    callback.onSecondWandSwing(
                        Pair(
                            player.world.getBlockAt(firstBlockLocation),
                            player.world.getBlockAt(secondBlockLocation)
                        ), selectedBlocks
                    )


                    //reset block location key
                    dataContainer.set(nameSpacedBlockBreakLocation, PersistentDataType.STRING, "")
                }
            } else {
                //setting the first block location
                dataContainer.set(
                    nameSpacedBlockBreakLocation,
                    PersistentDataType.STRING,
                    "${brokenBlock.location.x}, ${brokenBlock.location.y}, ${brokenBlock.location.z}"
                )
            }
        }
    }
}