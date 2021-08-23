package io.teamcheeze.plum.api.util.minecraft

/**
 * Used in LibConfig for nms version class types
 */
enum class NmsVersionType {
    /**
     * A pattern like v1_17_R1
     */
    CRAFTBUKKIT,

    /**
     * A pattern like 1_17 or 1_17_1
     */
    MINECRAFT
}