package io.github.teamcheeze.plum.api.util.core

interface Serializable: java.io.Serializable {
    fun serialize(): String
}