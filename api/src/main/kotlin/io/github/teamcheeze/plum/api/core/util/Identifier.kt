package io.github.teamcheeze.plum.api.core.util

/**
 * The identifier class. Used to determine either a instance's identifier matches the other
 * @author dolphin2410
 */
class Identifier(vararg val args: Any) {
    override fun equals(other: Any?): Boolean {
        if(other is Identifier && args.contentEquals(other.args)){
            return true
        }
        return false
    }
    override fun hashCode(): Int {
        return args.contentHashCode()
    }
}