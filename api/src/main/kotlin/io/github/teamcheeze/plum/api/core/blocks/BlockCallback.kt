package io.github.teamcheeze.plum.api.core.blocks

import org.bukkit.block.Block

/**
 * Callback function for block selection
 * @author dolphin2410
 */
interface BlockCallback {
    fun onFirstWandSwing(block: Block){}
    fun onSecondWandSwing(clicked: Pair<Block, Block>, blockList: ArrayList<Block>){}
}