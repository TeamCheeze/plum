package io.teamcheeze.plum.api.core.inventory.util

data class SlotRange(val columnRange: ColumnRange, val rowRange: RowRange) {
    override fun equals(other: Any?): Boolean {
        if (other is SlotRange) {
            if (this.columnRange == other.columnRange) {
                if (this.rowRange == other.rowRange) {
                    return true
                }
            }
        }
        return false
    }
}