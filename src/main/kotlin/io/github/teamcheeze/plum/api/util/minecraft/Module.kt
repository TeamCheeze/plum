package io.github.teamcheeze.plum.api.util.minecraft

import io.github.dolphin2410.jaw.reflection.ConstructorAccessor
import io.github.teamcheeze.plum.api.config.LibConfig

/**
 * NMS Module loading feature
 *
 * For example, Module<MyModule>("io.github.teamcheeze.v1_17_1.MyModuleImpl", "Hi")
 * If you have set up the LibConfig, using "{nms}" in the classname will automatically infer it
 */
class Module<T: Any>(val nmsClassName: String, vararg val parameters: Any) {
    @Suppress("unchecked_cast")
    fun getNmsInstance(): T {
        return ConstructorAccessor(Class.forName(nmsClassName.replace("{nms}", LibConfig.nmsPath))).newInstance(*parameters) as? T ?: throw Exception("The NMS Module is null")
    }
}