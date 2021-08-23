package io.teamcheeze.plum.api.util.collection

/**
 * @param p0 This contains the wildcard
 */
@Suppress("unchecked_cast")
fun matches(p0: ArrayList<String>, p1: ArrayList<String>, wildCard: String? = null): Boolean {
    val var0 = p0.clone() as ArrayList<String>
    val var1 = p1.clone() as ArrayList<String>

    if (var0.size != var1.size) {
        return false
    }

    val var2 = CollectionComparator.compare(var0, var1)
    wildCard?.let { wc ->
        var2.forEach {
            if (var0[it] != wc) {
                return false
            }
        }
    }
    return true
}