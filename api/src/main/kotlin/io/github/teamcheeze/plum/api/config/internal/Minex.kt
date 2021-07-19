package io.github.teamcheeze.plum.api.config.internal


/**
 * Minecraft version simple regex
 */
class Minex private constructor(minexString: String, private val unknown: Char = '#') {
    companion object {
        @JvmStatic
        fun of(minexString: String): Minex {
            return Minex(minexString)
        }
    }
    private val output = Property(minexString)

    /**
     * Replace the (index)th occurrence of '#' with value
     */
    operator fun set(index: Int, value: String) {
        output.set(output.get().replaceNth(index, unknown.toString(), value))
    }

    private fun invalidCases(): Array<Boolean> {
        return arrayOf(
            output.get().replace("\\((.+)\\)".toRegex(), "").contains(unknown),
            "\\(".toRegex().findAll(output.get()).toList().size != "\\)".toRegex().findAll(output.get()).toList().size
        )
    }

    fun validate() {
        // Throw exception is any of the invalid cases match
        if (invalidCases().any { it }) {
            throw RuntimeException("Invalid syntax!!")
        }
    }

    override fun toString(): String {
        return if(!output.get().contains(unknown)) {
            output.get().replace("(", "").replace(")", "")
        } else {
            validate()
            output.get().replace("\\((.+)\\)".toRegex(), "")
        }
    }
}