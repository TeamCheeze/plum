package io.github.teamcheeze.plum.api.util

class Group<T> private constructor(val identifier: T, val parent: Group<T>?) {
    constructor(identifier: T): this(identifier, null)
    val subGroups = ArrayList<Group<T>>()
//    fun <R>convertContent(action: (T)->R): Group<R> {
//        return Group(action.invoke(this.identifier)).apply {
//            FieldAccessor(this, "subGroups").set(subGroups.map {
//                convertContent(action)
//            })
//        }
//    }
    fun addGroup(value: T): Group<T> {
        return Group(value, this).also { subGroups.add(it) }
    }
    fun findGroup(value: T): Group<T> {
        return subGroups.find { it.identifier == value }!!
    }
    fun findGroupBy(action: (T)->Boolean): Group<T>? {
        return subGroups.find { action.invoke(this.identifier) }
    }
    fun findGroupOrNull(value: T): Group<T>? {
        return subGroups.find { it.identifier == value }
    }
}