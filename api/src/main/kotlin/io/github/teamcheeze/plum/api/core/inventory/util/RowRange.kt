package io.github.teamcheeze.plum.api.core.inventory.util

data class RowRange(val range: IntRange) {
    /**
     * Get the slot by also specifying the column
     * @param columnIndex The index of the column
     * @return The specified slot
     */
    fun column(column: Int): SlotRange {
        return column(Column(column))
    }

    override fun equals(other: Any?): Boolean {
        if (other is RowRange) {
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
    fun column(column: Column): SlotRange {
        return SlotRange(ColumnRange(column.index..column.index), this)
    }

    fun columnRange(range: ColumnRange): SlotRange {
        return SlotRange(range, this)
    }

    fun columnRange(range: IntRange): SlotRange {
        return columnRange(ColumnRange(range))
    }
}