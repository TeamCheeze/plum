package io.github.teamcheeze.plum.api.core.inventory.util

data class ColumnRange(val range: IntRange) {
    /**
     * Get the slot by also specifying the column
     * @param columnIndex The index of the column
     * @return The specified slot
     */
    fun row(row: Int): SlotRange {
        return row(Row(row))
    }

    override fun equals(other: Any?): Boolean {
        if (other is ColumnRange) {
            if (this.range == other.range) {
                return true
            }
        }
        return false
    }

    /**
     * Get the slot with an existing column instance
     * @param column The column
     * @return The specified slot
     */
    fun row(row: Row): SlotRange {
        return SlotRange(this, RowRange(row.index..row.index))
    }

    fun rowRange(range: RowRange): SlotRange {
        return SlotRange(this, range)
    }

    fun rowRange(range: IntRange): SlotRange {
        return rowRange(RowRange(range))
    }
}