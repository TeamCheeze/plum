package io.github.teamcheeze.plum.api.util

import java.lang.RuntimeException

class CollectionComparator {
    companion object {
        @JvmStatic
        fun <T> compare(one: ArrayList<T>, other: ArrayList<T>) {
            if (one.size != other.size) {
                throw RuntimeException("Length mismatch. Use compareDiffLen instead.")
            }
            compareDiffLen(one, other)
        }
        @JvmStatic
        fun greaterLen(one: ArrayList<*>, other: ArrayList<*>): ArrayList<*> {
            return when (true) {
                one.size > other.size -> {
                    one
                }
                one.size < other.size -> {
                    other
                }
                else -> {
                    one
                }
            }
        }
        @JvmStatic
        fun other(p0: ArrayList<*>, p1: Pair<ArrayList<*>, ArrayList<*>>): ArrayList<*> {
            return when (true) {
                p1.first == p0 -> {
                    p1.second
                }
                p1.second == p0 -> {
                    p1.first
                }
                else -> {
                    throw RuntimeException("None")
                }
            }
        }
        @JvmStatic
        fun <T> compareDiffLen(one: ArrayList<T>, other: ArrayList<T>): ArrayList<Int> {
            val matchingIndex = ArrayList<Int>()
            val greater = greaterLen(one, other)
            val smaller = other(greater, one to other)
            smaller.forEachIndexed { index, it ->
                if (it == greater[index]) {
                    matchingIndex.add(index)
                }
            }
            return matchingIndex
        }
    }
}