package io.teamcheeze.plum.api.core.inventory.component

import io.github.teamcheeze.plum.api.core.inventory.GInventory
import io.github.teamcheeze.plum.api.core.inventory.util.Slot
import io.github.teamcheeze.plum.api.util.core.Property
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

data class InventoryButton(val item: ItemStack, val inventory: GInventory, val slot: Slot): InventoryComponent {
    init {
        inventory.setItemAt(slot, item)
    }
    private val onClickAction: Property<(InventoryClickEvent)->Unit> = Property {}
    fun onClick(action: (InventoryClickEvent)->Unit) {
        onClickAction.set(action)
    }
}