package io.github.teamcheeze.plum.api.core.inventory.component

import io.github.teamcheeze.plum.api.core.inventory.GInventory
import io.github.teamcheeze.plum.api.core.inventory.util.Slot
import io.github.teamcheeze.plum.api.util.core.Property
import org.bukkit.inventory.ItemStack

data class InventoryInput(val inventory: GInventory, val slot: Slot): InventoryComponent {
    init {
        inventory.removeItemAt(slot)
    }
    private val required = Property(false)
    var isRequired: Boolean
        get() = required.get()
        set(value) {
            required.set(value)
        }
    private val onNoRequired = Property {}
    fun onRequirementNotMet(action: ()->Unit) {
        onNoRequired.set(action)
    }
    var value: ItemStack?
        get() {
            return if (isRequired && inventory.getItemAt(slot) == null) {
                onNoRequired.get().invoke()
                null
            } else {
                inventory.getItemAt(slot)
            }
        }
        set(value) {
            if (value == null && this.value != null) {
                inventory.inventory.removeItem(this.value)
            }
        }
}