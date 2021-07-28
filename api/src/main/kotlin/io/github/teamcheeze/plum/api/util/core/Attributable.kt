package io.github.teamcheeze.plum.api.util.core

interface Attributable<K, V> {
    fun getAttribute(attributeName: K): V?
    fun setAttribute(key: K, value: V)
    fun getAttributes(): Map<K, V>
    fun setAttributes(vararg pairs: Pair<K, V>)
}