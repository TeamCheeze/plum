package io.teamcheeze.plum.api.config

import io.github.teamcheeze.plum.api.util.core.Property
import io.github.teamcheeze.plum.api.util.minecraft.NmsVersionType
import org.bukkit.Bukkit

class LibConfig {
    companion object {
        @JvmStatic
        private var nmsPathInternal: Property<Property.UninitializedProperty<String>> = Property(Property.UninitializedProperty(String::class.java))

        @JvmStatic
        var nmsPath: String
            get() = nmsPathInternal.get().get()
            set(value) {
                nmsPathInternal.get().set(value)
            }

        @JvmStatic
        var nmsVersionType = NmsVersionType.CRAFTBUKKIT

        @JvmStatic
        var nmsVersionPattern: Property<String> = Property("#_#_R#")

        private val nmsVersion: String
            get() {
                return when(nmsVersionType) {
                    NmsVersionType.CRAFTBUKKIT->{
                        Bukkit.getServer()::class.javaObjectType.`package`.name.split(".")[3]
                    }
                    NmsVersionType.MINECRAFT->{
//                        Bukkit.getVersion()
                        throw RuntimeException("Not supported yet?")
                    }
                }
            }

        fun getNmsClassname(): String {
            return "$nmsPath.$nmsVersion"
        }
    }
}