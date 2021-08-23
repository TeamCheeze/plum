package io.github.teamcheeze.plum.api.core.inventory.util

import kotlin.math.floor

/**
 * Information about the slot
 */
data class Slot(val row: Row, val column: Column) {
    companion object {
        fun fromIndex(bukkitIndex: Int): Slot {
            val column = floor(bukkitIndex / 9.0)
            val row = bukkitIndex % 9
            return Slot(Row(row), Column(column.toInt()))
        }
    }
    override fun equals(other: Any?): Boolean {
        if (other is Slot) {
            if (this.row == other.row) {
                if (this.column == other.column) {
                    return true
                }
            }
        }
        return false
    }
    /**
     * The index of the slot currently in the inventory
     */
    val bukkitIndex = row.index * 9 + column.index

//    /**
//     * Get or set this field to modify the inventory
//     */
//    var item: ItemStack?
//        get() = inventory.getItem(index)
//        set(value) {
//            inventory.setItem(index, value)
//        }
}