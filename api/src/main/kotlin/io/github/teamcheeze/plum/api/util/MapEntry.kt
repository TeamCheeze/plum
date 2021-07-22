package io.github.teamcheeze.plum.api.util

data class MapEntry<K, V>(override val key: K, override val value: V): Map.Entry<K,V>