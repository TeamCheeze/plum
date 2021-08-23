package io.teamcheeze.plum.api.core.inventory.util

/**
 * Information about the row
 */
data class Row(val index: Int) {
    /**
     * Get the slot by also specifying the column
     * @param columnIndex The index of the column
     * @return The specified slot
     */
    fun column(columnIndex: Int): Slot {
        return column(Column(columnIndex))
    }

    override fun equals(other: Any?): Boolean {
        if (other is Row) {
            if (this.index == other.index) {
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
    fun column(column: Column): Slot {
        return Slot(this, column)
    }

    fun columnRange(range: ColumnRange): SlotRange {
        return SlotRange(range, RowRange(index..index))
    }

    fun columnRange(range: IntRange): SlotRange {
        return columnRange(ColumnRange(range))
    }
}