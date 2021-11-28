package io.github.teamcheeze.plum.api.util.core

import io.github.teamcheeze.jaw.reflection.FieldAccessor

class TypedGroup<T, E : Enum<E>, A> private constructor(
    val identifier: T,
    val type: E,
    val data: A,
    val parent: TypedGroup<T, E, A>?,
    val root: TypedGroup<T, E, A>?,
    val parents: ArrayList<TypedGroup<T, E, A>>
) {
    constructor(identifier: T, type: E, data: A) : this(identifier, type, data, null, null, arrayListOf()) {
        FieldAccessor(this, "root").set(this)
    }

    val subGroups = ArrayList<TypedGroup<T, E, A>>()
    fun addGroup(value: T, type: E, data: A): TypedGroup<T, E, A> {
        return TypedGroup(value, type, data, this, root, parents.apply { add(this@TypedGroup) }).also { subGroups.add(it) }
    }

    fun findGroup(value: T): TypedGroup<T, E, A> {
        return subGroups.find { it.identifier == value }!!
    }

    fun findGroupBy(action: (T) -> Boolean): TypedGroup<T, E, A>? {
        return subGroups.find { action.invoke(this.identifier) }
    }

    fun findGroupOrNull(value: T): TypedGroup<T, E, A>? {
        return subGroups.find { it.identifier == value }
    }
}