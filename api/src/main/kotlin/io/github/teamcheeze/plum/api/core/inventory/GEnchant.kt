package io.github.teamcheeze.plum.api.core.inventory

import org.bukkit.enchantments.Enchantment

/**
 * A enchantment data class that contains data about the enchant, level, and whether the level overrided the default enchant level
 * @author dolphin2410
 */
data class GEnchant(val enchantment: Enchantment, val level: Int, val overrideLevel: Boolean)