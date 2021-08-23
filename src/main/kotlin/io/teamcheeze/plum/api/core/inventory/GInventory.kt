package io.teamcheeze.plum.api.core.inventory

import io.github.teamcheeze.plum.api.core.bukkit.GBukkit
import io.github.teamcheeze.plum.api.core.events.EventRegistry
import io.github.teamcheeze.plum.api.core.inventory.component.InventoryButton
import io.github.teamcheeze.plum.api.core.inventory.component.InventoryInput
import io.github.teamcheeze.plum.api.core.inventory.util.Column
import io.github.teamcheeze.plum.api.core.inventory.util.GInventoryClickEvent
import io.github.teamcheeze.plum.api.core.inventory.util.Row
import io.github.teamcheeze.plum.api.core.inventory.util.Slot
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * An inventory with extra features
 * @author dolphin2410
 */
class GInventory private constructor(val inventory: Inventory, val rows: Int) {

    val onClick = ArrayList<(InventoryClickEvent)->Unit>()
    val onOpen = ArrayList<(InventoryOpenEvent)->Unit>()
    val onClose = ArrayList<(InventoryCloseEvent)->Unit>()
    private val allSlots = ArrayList<Slot>()
    init {
        for (row in 0 until rows) {
            for (column in 0 until 9) {
                allSlots.add(Slot(Row(row), Column(column)))
            }
        }
        EventRegistry.register<InventoryClickEvent> { e ->
            GBukkit.server.pluginManager.callEvent(GInventoryClickEvent(e, Slot.fromIndex(e.slot), this))
            if (e.inventory == inventory) {
                onClick.forEach {
                    it.invoke(e)
                }
            }
        }
        EventRegistry.register<InventoryOpenEvent> { e ->
            if (e.inventory == inventory) {
                onOpen.forEach {
                    it.invoke(e)
                }
            }
        }
        EventRegistry.register<InventoryCloseEvent> { e ->
            if (e.inventory == inventory) {
                onClose.forEach {
                    it.invoke(e)
                }
            }
        }
    }

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
                throw LimitExceedException("Number of rows should be between 1 and 6")
            }
            val newInventory = Bukkit.createInventory(null, rows * 9, name)
            return GInventory(newInventory, rows)
        }
    }

    /**
     * Get the row
     * @param rowIndex The index of the row
     * @return The row
     */
    fun row(rowIndex: Int): Row {
        if (rowIndex !in 0..5) {
            throw Exception("This is an index starting from 0. It also should not be greater than 5")
        }
        return if (rowIndex * 9 <= inventory.size) {
            Row(rowIndex)
        } else {
            throw Exception("Row out of Inventory bounds")
        }
    }
    private val occupiedFields = ArrayList<Slot>()
    fun addButton(item: ItemStack, location: Slot): InventoryButton {
        return InventoryButton(item, this, location).also {
            occupiedFields.add(location)
            EventRegistry.register<GInventoryClickEvent> { e ->
                if (e.inventory == this) {
                    if (e.clickedSlot == location) {
                        e.isCancelled = true
                    }
                }
            }
        }
    }

    fun setBackground(item: ItemStack) {
        allSlots.filter { !occupiedFields.contains(it) }.forEach {
            setItemAt(it, item)
            EventRegistry.register<GInventoryClickEvent> { e ->
                if (e.inventory == this) {
                    if (e.clickedSlot == it) {
                        e.isCancelled = true
                    }
                }
            }
        }
    }

    fun addInputField(location: Slot): InventoryInput {
        return InventoryInput(this, location).also {
            occupiedFields.add(location)
        }
    }

    /**
     * Get the column
     * @param columnIndex The index of the column
     * @return The column
     */
    fun column(columnIndex: Int): Column {
        if (columnIndex !in 0..8) {
            throw Exception("This is an index starting from 0. It also should not be greater than 8")
        }
        return Column(columnIndex)
    }

    fun getItemAt(slot: Slot): ItemStack? {
        return inventory.getItem(slot.bukkitIndex)
    }
    fun setItemAt(slot: Slot, item: ItemStack?) {
        if (item == null) {
            removeItemAt(slot)
        } else {
            inventory.setItem(slot.bukkitIndex, item)
        }
    }
    fun removeItemAt(slot: Slot) {
        inventory.removeItem(getItemAt(slot))
    }

    /**
     * Specify the action on click
     * @param action Action to invoke when the event is triggered
     */
    fun onClick(action: (InventoryClickEvent) -> Unit) {
        onClick.add(action)
    }
    fun onOpen(action: (InventoryOpenEvent)->Unit) {
        onOpen.add(action)
    }
    fun onClose(action: (InventoryCloseEvent)->Unit) {
        onClose.add(action)
    }
}