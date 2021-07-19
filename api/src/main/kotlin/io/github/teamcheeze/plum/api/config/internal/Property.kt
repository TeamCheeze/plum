package io.github.teamcheeze.plum.api.config.internal

import java.lang.NullPointerException

class Property<T>(private var internal: T) {
    data class UninitializedProperty<T>(val clazz: Class<T>){
        private var internal: T? = null
        fun getOrNull(): T? {
            return internal
        }
        fun get(): T {
            return if (internal != null) {
                internal!!
            } else {
                throw NullPointerException("The uninitialized property has not been yet initialized")
            }
        }
        fun set(value: T) {
            internal = value
        }
    }
    companion object {
        fun <T> uninitializedType(clazz: Class<T>): Property<UninitializedProperty<T>> {
            return Property(UninitializedProperty(clazz))
        }
    }
    fun get(): T {
        return internal
    }
    fun set(item: T) {
        internal = item
    }
    fun modify(action: (T)->Unit) {
        action.invoke(internal)
    }
}