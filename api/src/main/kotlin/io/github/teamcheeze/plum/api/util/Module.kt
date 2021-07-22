package io.github.teamcheeze.plum.api.util

import io.github.teamcheeze.plum.api.config.LibConfig
import io.github.dolphin2410.jaw.reflection.ConstructorAccessor

class Module<T: Any>(val nmsClassName: String, vararg val parameters: Any) {
    @Suppress("unchecked_cast")
    fun getNmsInstance(): T {
        return ConstructorAccessor(Class.forName(nmsClassName.replace("{nms}", LibConfig.nmsPath))).newInstance(*parameters) as? T ?: throw Exception("The NMS Module is null")
    }
}