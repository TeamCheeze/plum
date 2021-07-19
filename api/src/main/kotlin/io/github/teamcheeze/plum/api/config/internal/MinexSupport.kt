package io.github.teamcheeze.plum.api.config.internal

fun String.replaceNth(index: Int, old: String, new: String): String {
    old.toRegex().findAll(this).forEachIndexed { _index, it ->
        if (_index == index) {
            return this.replaceRange(it.range, new)
        }
    }
    throw RuntimeException("Cannot replace nth as the current index isn't found.")
}
fun Array<Boolean>.any(): Boolean {
    this.forEach {
        if (it) {
            return true
        }
    }
    return false
}
fun Array<Boolean>.all(): Boolean {
    this.forEach {
        if (!it) {
            return !it
        }
    }
    return true
}