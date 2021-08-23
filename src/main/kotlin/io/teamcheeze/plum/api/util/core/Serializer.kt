package io.teamcheeze.plum.api.util.core

class Serializer {
    companion object {
        /**
         * The serialized String is in format <classname>;<param_1>;<param_2>;<param_n>
         */
        @JvmStatic
        fun <T> serialize(clazz: Class<T>, vararg objs: Any): String {
            val data = ArrayList<String>()
            data.add(clazz.name)
            data.addAll(objs.map { it.toString() })
            return data.toTypedArray().joinToString(";")
        }
    }
}