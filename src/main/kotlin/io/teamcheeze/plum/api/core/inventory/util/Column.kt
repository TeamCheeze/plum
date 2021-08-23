package io.teamcheeze.plum.api.core.inventory.util

/**
 * Information about the column
 */
data class Column(val index: Int) {
    /**
     * Get the slot by also specifying the row
     * @param rowIndex The index of the row
     * @return The specified slot
     */
    fun row(rowIndex: Int): Slot {
        return row(Row(rowIndex))
    }

    /**
     * Get the slot with an existing row instance
     * @param row The row
     * @return The specified slot
     */
    fun row(row: Row): Slot {
        return Slot(row, this)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Column) {
            if (this.index == other.index) {
                return true
            }
        }
        return false
    }

    fun rowRange(range: RowRange): SlotRange {
        return SlotRange(ColumnRange(index..index), range)
    }

    fun rowRange(range: IntRange): SlotRange {
        return rowRange(RowRange(range))
    }
}