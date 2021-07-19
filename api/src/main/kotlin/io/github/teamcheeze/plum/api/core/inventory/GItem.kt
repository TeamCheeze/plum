package io.github.teamcheeze.plum.api.core.inventory

import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * An ItemStack manager
 * @author dolphin2410
 */
data class GItem(val item: ItemStack) {
    companion object {
        /**
         * Create an item with the specified information
         * @param material The material
         * @param name The display name of the item. Default is the name of the material
         * @param enchantment A list of enchants. Default is an empty list
         * @param lore A list of lores. Default is an empty list
         * @param flag A list of flags. Default is an empty list
         * @return The item created with the information
         */
        fun create(material: Material, name: String = material.name, enchantment: List<GEnchant> = listOf(), lore: List<String> = listOf(), flag: List<ItemFlag> = listOf()): GItem {
            return GItem(ItemStack(material).apply {
                itemMeta?.setDisplayName(name)
                itemMeta?.lore = lore
                itemMeta?.addItemFlags(*flag.toTypedArray())
                enchantment.forEach {
                    itemMeta?.addEnchant(it.enchantment, it.level, it.overrideLevel)
                }
            })
        }
    }
}