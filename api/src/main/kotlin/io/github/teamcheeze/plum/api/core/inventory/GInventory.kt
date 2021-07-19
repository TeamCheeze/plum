package io.github.teamcheeze.plum.api.core.inventory

import io.github.teamcheeze.plum.api.core.events.manager.EventRegistry
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * An inventory with extra features
 * @author dolphin2410
 */
class GInventory private constructor(val inventory: Inventory) {
    /**
     * This class is called when you exceeded the max limit of a field
     */
    class LimitExceedException(msg: String) : Exception(msg)
    companion object {
        /**
         * Creating a new inventory
         * @param name The title of the inventory
         * @param rows The number of rows. Range from 1 to 6
         * @return A created GInventory instance
         */
        @JvmStatic
        fun create(name: String, rows: Int): GInventory {
            if (rows !in 1..6) {
                throw LimitExceedException("Row should be between 1 and 6")
            }
            val newInventory = Bukkit.createInventory(null, rows * 9, name)
            return GInventory(newInventory)
        }
    }

    /**
     * Information about the slot
     */
    data class Slot(val inventory: Inventory, val row: Row, val column: Column) {
        /**
         * Information about the column
         */
        data class Column(private val inventory: Inventory, val index: Int) {
            /**
             * Get the slot by also specifying the row
             * @param rowIndex The index of the row
             * @return The specified slot
             */
            fun row(rowIndex: Int): Slot {
                return row(Row(inventory, rowIndex))
            }

            /**
             * Get the slot with an existing row instance
             * @param row The row
             * @return The specified slot
             */
            fun row(row: Row): Slot {
                return Slot(inventory, row, this)
            }
        }

        /**
         * Information about the row
         */
        data class Row(private val inventory: Inventory, val index: Int) {
            /**
             * Get the slot by also specifying the column
             * @param columnIndex The index of the column
             * @return The specified slot
             */
            fun column(columnIndex: Int): Slot {
                return column(Column(inventory, columnIndex))
            }

            /**
             * Get the slot with an existing column instance
             * @param column The column
             * @return The specified slot
             */
            fun column(column: Column): Slot {
                return Slot(inventory, this, column)
            }
        }

        /**
         * The index of the slot currently in the inventory
         */
        val index = row.index * 9 + column.index

        /**
         * Get or set this field to modify the inventory
         */
        var item: ItemStack?
            get() = inventory.getItem(index)
            set(value) {
                inventory.setItem(index, value)
            }
    }

    /**
     * Get the row
     * @param rowIndex The index of the row
     * @return The row
     */
    fun row(rowIndex: Int): Slot.Row {
        if(rowIndex !in 0..5) {
            throw Exception("This is an index starting from 0. It also should not be greater than 5")
        }
        return if (rowIndex * 9 <= inventory.size) {
            Slot.Row(inventory, rowIndex)
        } else {
            throw Exception("Row out of Inventory bounds")
        }
    }

    /**
     * Get the column
     * @param columnIndex The index of the column
     * @return The column
     */
    fun column(columnIndex: Int): Slot.Column {
        if(columnIndex !in 0..8) {
            throw Exception("This is an index starting from 0. It also should not be greater than 8")
        }
        return Slot.Column(inventory, columnIndex)
    }

    /**
     * Specify the action on click
     * @param action Action to invoke when the event is triggered
     */
    fun onClick(action: (InventoryClickEvent) -> Unit) {
        EventRegistry.register(action)
    }
}