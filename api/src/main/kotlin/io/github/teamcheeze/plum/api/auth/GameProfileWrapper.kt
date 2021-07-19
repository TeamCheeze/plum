package io.github.teamcheeze.plum.api.auth

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import io.github.teamcheeze.plum.api.modules.playerManager.PlayerManager
import io.github.dolphin2410.jaw.reflection.FieldAccessor
import java.util.*

/**
 * A wrapper for mojang-authlib GameProfile. Used when and adding a dependency for it is unnecessary
 * @constructor This is the same as the original GameProfile's constructor, but it has an additional texture parameter to set the parameter on initialize
 * @param uuid The UUID for the profile
 * @param name The name for the profile
 * @param texture The texture for the profile
 * @author dolphin2410
 */
data class GameProfileWrapper(
    var uuid: UUID = UUID.randomUUID(),
    var name: String = "",
    var texture: TextureProfile = TextureProfile.Empty
) {
    /**
     * Unwraps the wrapper to the mojang-authlib GameProfile
     * @return The mojang-authlib GameProfile instance, but it is casted to Any
     * @throws IllegalArgumentException This is thrown when creating a GameProfile instance with both an empty name and uuid
     */
    fun unwrap(): Any {
        return GameProfile(uuid, name).also {
            it.properties.put(
                "textures",
                Property("textures", texture.value, texture.signature)
            )
        }
    }

    /**
     * A data class that typically does nothing. It is used to classify strings that contains the data of any names
     * @constructor This constructor contains the name with the string type
     * @param name The name
     * @author dolphin2410
     */
    data class NameProfile(val name: String) {
        /**
         * @return This returns the name
         */
        override fun toString(): String {
            return name
        }
    }

    /**
     * A data class that contains the data of textures. It contains the value and signature of a texture.
     * @constructor This is the base constructor for this class
     * @param value Value field given to a texture
     * @param signature Signature field given to a texture
     * @author dolphin2410
     */
    data class TextureProfile(val value: String, val signature: String) {
        /**
         * Loads the constructor based on the offline player's name. This is very dangerous because it changes the data of this class asynchronously. The completable future is to be contained on the next major update
         * @param name The name profile
         */
        constructor(name: NameProfile) : this("", "") {
            loadFromName(name.toString())
        }

        /**
         * Loads the constructor based on the offline player's name. This is very dangerous because it changes the data of this class asynchronously. The completable future is to be contained on the next major update
         * @param name The name
         */
        constructor(name: String) : this("", "") {
            loadFromName(name)
        }

        companion object {
            /**
             * An empty TextureProfile object that doesn't contain any data
             */
            @JvmStatic
            val Empty
                get() = TextureProfile("", "")
        }

        /**
         * Converts TextureProfile to a pair
         * @return A pair with <value, signature> format
         */
        fun toPair(): Pair<String, String> {
            return value to signature
        }

        /**
         * A private class that loads texture data from mojang's api server to the instance.
         * @return The current instance that this method is called from.
         */
        private fun loadFromName(name: String): TextureProfile {
            PlayerManager.getTexture(name) {
                FieldAccessor(this, "value").set(it.first)
                FieldAccessor(this, "signature").set(it.second)
            }
            return this
        }
    }
}