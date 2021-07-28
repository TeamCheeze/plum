package io.github.teamcheeze.plum.api.core.inventory

import io.github.teamcheeze.plum.api.util.core.Attributable
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * An ItemStack manager
 * @author dolphin2410
 */
data class GItem(val item: ItemStack): Attributable<String, Any> {
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
                val newItemMeta = itemMeta
                newItemMeta?.setDisplayName(name)
                newItemMeta?.lore = lore
                newItemMeta?.addItemFlags(*flag.toTypedArray())
                enchantment.forEach {
                    newItemMeta?.addEnchant(it.enchantment, it.level, it.overrideLevel)
                }
                itemMeta = newItemMeta
            })
        }
    }

    private val internal = HashMap<String, Any>()

    override fun getAttribute(attributeName: String): Any? {
        return internal[attributeName]
    }

    override fun setAttribute(key: String, value: Any) {
        internal[key] = value
    }

    override fun getAttributes(): Map<String, Any> {
        return internal
    }

    override fun setAttributes(vararg pairs: Pair<String, Any>) {
        pairs.forEach {
            internal[it.first] = it.second
        }
    }
}